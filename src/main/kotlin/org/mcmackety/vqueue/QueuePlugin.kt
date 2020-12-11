package org.mcmackety.vqueue

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException
import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.connection.DisconnectEvent
import com.velocitypowered.api.event.player.PlayerChooseInitialServerEvent
import com.velocitypowered.api.event.player.ServerPreConnectEvent
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.plugin.annotation.DataDirectory
import com.velocitypowered.api.proxy.Player
import com.velocitypowered.api.proxy.ProxyServer
import com.velocitypowered.api.proxy.server.RegisteredServer
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.mcmackety.vqueue.config.QueueConfig
import org.mcmackety.vqueue.config.YAMLParse
import org.slf4j.Logger
import java.io.File
import java.lang.Exception
import java.nio.file.Path
import java.util.concurrent.TimeUnit

@Plugin(id = "velocityqueue", version = "1.0.0-SNAPSHOT", name = "Velocity Queue", authors = ["McMackety"])
class QueuePlugin @Inject constructor(proxyServer: ProxyServer, logger: Logger, @DataDirectory dataDirectory: Path) {
    companion object {
        lateinit var proxyServer: ProxyServer
            private set
        lateinit var logger: Logger
            private set
        lateinit var instance: QueuePlugin
            private set
        lateinit var config: QueueConfig
            private set
    }

    private var serverToMaxPlayers: MutableMap<RegisteredServer, Int> = mutableMapOf()
    private var joinQueue: Queue = Queue()
    private var serverToQueue: MutableMap<RegisteredServer, Queue> = mutableMapOf()
    private var playerToServerToSwitch: MutableMap<Player, RegisteredServer> = mutableMapOf()
    private var playerToQueue: MutableMap<Player, Queue> = mutableMapOf()

    init {
        QueuePlugin.proxyServer = proxyServer
        QueuePlugin.logger = logger
        instance = this


        val dataDirFile = dataDirectory.toFile()
        if (!dataDirFile.exists()) {
            dataDirFile.mkdir()
        }

        val file = File(dataDirectory.toFile(), "config.yaml")
        if (file.exists()) {
            try {
                config = YAMLParse.parseFile(dataDirectory, "config.yaml", QueueConfig::class)
            } catch (e: UnrecognizedPropertyException) {
                config = QueueConfig()
                YAMLParse.writeFile(dataDirectory, "config.yaml", config)
            }
        } else {
            config = QueueConfig()
            YAMLParse.writeFile(dataDirectory, "config.yaml", config)
        }
    }

    @Suppress("UNUSED_PARAMETER")
    @Subscribe
    fun onProxyInitialization(event: ProxyInitializeEvent) {
        proxyServer.scheduler.buildTask(this) {
            val servers = proxyServer.allServers
            for (server in servers) {
                server.ping().thenAccept { ping ->
                    ping.players.ifPresent { players ->
                        serverToMaxPlayers[server] = players.max
                    }
                }
            }
        }.repeat(5, TimeUnit.SECONDS).schedule()

        proxyServer.scheduler.buildTask(this) {
            val next = joinQueue.next()
            if (next != null) {
                if (!serverToMaxPlayers.containsKey(next.server)) return@buildTask
                if (next.server.playersConnected.size < serverToMaxPlayers[next.server]!!) {
                    val queuePlayer = proxyServer.getPlayer(next.uuid)
                    joinQueue.remove(next)
                    joinQueue.broadcastIndexMessage(
                        config.settings.joinQueue.oneLessPlayerInQueueMessage,
                        config.settings.joinQueue.lastPlayerInQueueMessage
                    )
                    queuePlayer.ifPresent { player ->
                        playerToQueue.remove(player)
                        player.sendMessage(MiniMessage.get().parse(config.settings.joinQueue.joinedServerMessage))
                        player.createConnectionRequest(next.server).fireAndForget()
                    }
                }
            }
        }.repeat(1, TimeUnit.SECONDS).schedule()
    }

    @Subscribe
    fun onPlayerChooseInitialServer(event: PlayerChooseInitialServerEvent) {
        if (config.settings.joinQueue.enabled) {
            event.initialServer.ifPresent { initialServer ->
                if (playerToQueue.containsKey(event.player)) {
                    playerToQueue[event.player]!!.removeUUID(event.player.uniqueId)
                    playerToQueue.remove(event.player)
                }
                if (!serverToMaxPlayers.containsKey(initialServer)) {
                    logger.error("No limbo servers are registered, couldn't send ${event.player.username} to a limbo server.")
                    event.player.disconnect(Component.text("No limbo servers are registered, couldn't send ${event.player.username} to a limbo server."))
                    return@ifPresent
                }
                if (serverToMaxPlayers[initialServer]!! <= initialServer.playersConnected.size) {
                    for (limbo in config.settings.limboServers) {
                        val server = proxyServer.getServer(limbo.name)
                        if (server.isPresent) {
                            event.player.sendMessage(
                                MiniMessage.get().parse(config.settings.joinQueue.joinedQueueMessage)
                            )
                            joinQueue.add(QueuePlayer(event.player.uniqueId, initialServer))
                            playerToQueue[event.player] = joinQueue
                            event.setInitialServer(server.get())
                            return@ifPresent
                        }
                    }
                    logger.error("No limbo servers are registered, couldn't send ${event.player.username} to a limbo server.")
                    event.player.disconnect(Component.text("No limbo servers are registered, couldn't send ${event.player.username} to a limbo server."))
                }
            }
        }
    }

    @Subscribe
    fun onPlayerSwitchServer(event: ServerPreConnectEvent) {

        if (config.settings.intraServerQueue.enabled) {
            if (playerToServerToSwitch.containsKey(event.player)) {
                if (playerToServerToSwitch[event.player]!! == event.originalServer) {
                    playerToServerToSwitch.remove(event.player)
                    return
                }
            }
            if (isQueuedServer(event.result)) {
                if (playerToQueue.containsKey(event.player)) {
                    playerToQueue[event.player]!!.removeUUID(event.player.uniqueId)
                    playerToQueue.remove(event.player)
                }
                val server = event.result.server.get()
                initQueuedServer(server)
                val queue = serverToQueue[server]!!
                queue.add(QueuePlayer(event.player.uniqueId, server))
                playerToQueue[event.player] = queue
                event.player.sendMessage(
                    MiniMessage.get().parse(config.settings.intraServerQueue.joinedQueueMessage)
                )
                event.result = ServerPreConnectEvent.ServerResult.denied()
            }
        }
    }

    @Subscribe
    fun onPlayerLeave(event: DisconnectEvent) {
        if (playerToQueue.containsKey(event.player)) {
            playerToQueue[event.player]!!.removeUUID(event.player.uniqueId)
            playerToQueue.remove(event.player)
        }
        if (playerToServerToSwitch.containsKey(event.player)) {
            playerToServerToSwitch.remove(event.player)
        }
    }

    fun leaveQueue(player: Player): Boolean {
        var leftQueue = false
        if (playerToQueue.containsKey(player)) {
            playerToQueue[player]!!.removeUUID(player.uniqueId)
            playerToQueue.remove(player)
            leftQueue = true
        }
        if (playerToServerToSwitch.containsKey(player)) {
            playerToServerToSwitch.remove(player)
            leftQueue = true
        }
        return leftQueue
    }

    private fun initQueuedServer(server: RegisteredServer) {
        if (!serverToQueue.containsKey(server)) {
            serverToQueue[server] = Queue()
            val queue = serverToQueue[server]!!
            proxyServer.scheduler.buildTask(this) {
                val next = queue.next()
                if (next != null) {
                    if (!serverToMaxPlayers.containsKey(next.server)) return@buildTask
                    if (next.server.playersConnected.size < serverToMaxPlayers[next.server]!!) {
                        val queuePlayer = proxyServer.getPlayer(next.uuid)
                        queue.remove(next)
                        queue.broadcastIndexMessage(
                            config.settings.intraServerQueue.oneLessPlayerInQueueMessage,
                            config.settings.intraServerQueue.lastPlayerInQueueMessage
                        )
                        queuePlayer.ifPresent { player ->
                            playerToQueue.remove(player)
                            player.sendMessage(
                                MiniMessage.get().parse(config.settings.intraServerQueue.joinedServerMessage)
                            )
                            playerToServerToSwitch[player] = server
                            player.createConnectionRequest(next.server).fireAndForget()
                        }
                    }
                }
            }.repeat(config.settings.intraServerQueue.millisecondsPerPlayer.toLong(), TimeUnit.MILLISECONDS)
                .schedule()
        }
    }

    private fun isQueuedServer(resultServer: ServerPreConnectEvent.ServerResult): Boolean {
        if (resultServer.server.isEmpty) return false
        for (server in config.settings.intraServerQueue.queuedServers) {
            if (server == resultServer.server.get().serverInfo.name) {
                return true
            }
        }
        return false
    }
}