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
import com.velocitypowered.api.proxy.ConnectionRequestBuilder
import com.velocitypowered.api.proxy.Player
import com.velocitypowered.api.proxy.ProxyServer
import com.velocitypowered.api.proxy.server.RegisteredServer
import com.velocitypowered.api.proxy.server.ServerPing
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.Template
import org.mcmackety.vqueue.config.QueueConfig
import org.mcmackety.vqueue.config.YAMLParse
import org.mcmackety.vqueue.queue.LocalQueue
import org.mcmackety.vqueue.queue.Queue
import org.slf4j.Logger
import java.io.File
import java.nio.file.Path
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

@Plugin(id = "velocityqueue", version = "1.1.1-SNAPSHOT", name = "Velocity Queue", authors = ["McMackety"])
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
    private var joinQueue: Queue
    private var serverToQueue: MutableMap<RegisteredServer, Queue> = mutableMapOf()
    private var playerToServerToSwitch: MutableMap<Player, RegisteredServer> = mutableMapOf()
    private var playerToQueue: MutableMap<Player, Queue> = mutableMapOf()
    private var playersToTries: MutableMap<QueuePlayer, AtomicInteger> = mutableMapOf()

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

        joinQueue = createQueue()

        proxyServer.commandManager.register("leavequeue", LeaveQueue(this), "lq")
    }

    @Suppress("UNUSED_PARAMETER")
    @Subscribe
    fun onProxyInitialization(event: ProxyInitializeEvent) {
        proxyServer.scheduler.buildTask(this) {
            val servers = proxyServer.allServers
            for (server in servers) {
                try {
                    server.ping().thenAccept { ping ->
                        ping.players.ifPresent { players ->
                            serverToMaxPlayers[server] = players.max
                        }
                    }
                } catch (e: Exception) {
                }
            }
        }.repeat(5, TimeUnit.SECONDS).schedule()

        proxyServer.scheduler.buildTask(this) {
            try {
                val next = joinQueue.next()
                next?.server?.ping()?.thenApply { serverPing: ServerPing ->
                    if (serverPing.players.isPresent && next.server.playersConnected.size + 1 <= serverPing.players.get().max) {
                        val queuePlayer = proxyServer.getPlayer(next.uuid)
                        queuePlayer.ifPresent { player ->
                            println(next.server.serverInfo.name)
                            player.createConnectionRequest(next.server).fireAndForget()
                            joinQueue.remove(next)
                            joinQueue.broadcastIndexMessage(
                                next.server.serverInfo.name,
                                config.settings.joinQueue.oneLessPlayerInQueueMessage,
                                config.settings.joinQueue.lastPlayerInQueueMessage
                            )
                            joinQueue.broadcastIndexMessage(
                                next.server.serverInfo.name,
                                config.settings.joinQueue.oneLessPlayerInQueueMessage,
                                config.settings.joinQueue.lastPlayerInQueueMessage
                            )
                            playersToTries.remove(next)
                            return@ifPresent
                        }
                    }
                }
            } catch (e: Exception) {
            }
        }.repeat(1, TimeUnit.SECONDS).schedule()
        proxyServer.scheduler.buildTask(this) {
            val next = joinQueue.next()
            if (next != null) {
                joinQueue.broadcastIndexMessage(
                    next.server.serverInfo.name,
                    config.settings.intraServerQueue.oneLessPlayerInQueueMessage,
                    config.settings.intraServerQueue.lastPlayerInQueueMessage
                )
            }
        }.repeat(config.settings.joinQueue.playerInQueueTimePerMessage, TimeUnit.MILLISECONDS)
            .schedule()
    }

    fun getPlayerPriority(player: Player): Long {
        for (i in 0..10) {
            if (player.hasPermission("velocityqueue.priority.$i")) {
                return i.toLong()
            }
        }
        return 0
    }

    @Subscribe
    fun onPlayerChooseInitialServer(event: PlayerChooseInitialServerEvent) {
        if (config.settings.joinQueue.enabled) {
            event.initialServer.ifPresent { initialServer ->
                if (event.player.hasPermission("velocityqueue.skipjoinqueue")) return@ifPresent
                if (playerToQueue.containsKey(event.player)) {
                    playerToQueue[event.player]!!.removeUUID(event.player.uniqueId)
                    playerToQueue.remove(event.player)
                }
                if (serverToMaxPlayers[initialServer]!! <= initialServer.playersConnected.size) {
                    if (config.settings.limboServers.isNotEmpty()) {
                        val limbo = config.settings.limboServers.shuffled()
                            .take(1)[0] // Take a random limboServer, this should work for load balancing for now.
                        val server = proxyServer.getServer(limbo.name)
                        if (server.isPresent) {
                            event.player.sendMessage(
                                MiniMessage.get().parse(
                                    config.settings.joinQueue.joinedQueueMessage,
                                    Template.of("playerName", event.player.username),
                                    Template.of("destServer", initialServer.serverInfo.name)
                                )
                            )
                            joinQueue.add(
                                QueuePlayer(
                                    event.player.uniqueId,
                                    initialServer,
                                    LocalDateTime.now(),
                                    getPlayerPriority(event.player)
                                )
                            )
                            playerToQueue[event.player] = joinQueue
                            event.setInitialServer(server.get())
                            return@ifPresent
                        }
                    }
                    logger.error("E1: No limbo servers are registered, couldn't send ${event.player.username} to a limbo server.")
                    event.player.disconnect(Component.text("No limbo servers are registered, couldn't send ${event.player.username} to a limbo server."))
                }
            }
        }
    }

    @Subscribe
    fun onPlayerSwitchServer(event: ServerPreConnectEvent) {
        if (config.settings.joinQueue.enabled) {
            if (joinQueue.contains(event.player.uniqueId)) {
                if (event.player.hasPermission("velocityqueue.skipjoinqueue")) return
                var isLimbo = false
                for (limbo in config.settings.limboServers) {
                    if (limbo.name == event.originalServer.serverInfo.name) {
                        isLimbo = true
                    }
                }
                if (isLimbo && event.player.uniqueId == joinQueue.next()?.uuid) {
                    return
                }
                event.result = ServerPreConnectEvent.ServerResult.denied()
                return
            }
        }
        if (config.settings.intraServerQueue.enabled) {
            if (event.player.hasPermission("velocityqueue.skipintraqueue")) return
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
                queue.add(
                    QueuePlayer(
                        event.player.uniqueId,
                        server,
                        LocalDateTime.now(),
                        getPlayerPriority(event.player)
                    )
                )
                playerToQueue[event.player] = queue
                event.player.sendMessage(
                    MiniMessage.get().parse(
                        config.settings.intraServerQueue.joinedQueueMessage,
                        Template.of("playerName", event.player.username),
                        Template.of("destServer", server.serverInfo.name)
                    )
                )
                if (!event.player.currentServer.isPresent) {
                    if (config.settings.limboServers.isNotEmpty()) {
                        val limbo = config.settings.limboServers.shuffled()
                            .take(1)[0] // Take a random limboServer, this should work for load balancing for now.
                        val server = proxyServer.getServer(limbo.name)
                        if (server.isPresent) {
                            event.result = ServerPreConnectEvent.ServerResult.allowed(server.get())
                            return
                        }
                    }
                    logger.error("E5: No limbo servers are registered, couldn't send ${event.player.username} to a limbo server.")
                    event.player.disconnect(Component.text("No limbo servers are registered, couldn't send ${event.player.username} to a limbo server."))
                    return
                }
                event.result = ServerPreConnectEvent.ServerResult.denied()
            }
        }
    }

    @Subscribe
    fun onPlayerLeave(event: DisconnectEvent) {
        if (playerToQueue.containsKey(event.player)) {
            try {
                playerToQueue[event.player]!!.removeUUID(event.player.uniqueId)
                playerToQueue.remove(event.player)
            } catch (e: Exception) {
            }
        }
        if (playerToServerToSwitch.containsKey(event.player)) {
            playerToServerToSwitch.remove(event.player)
        }
    }

    /**
     * Makes a player exit the queue they are currently in. (No message is sent when this is called.)
     * @param player The player that wants to leave the queue.
     */
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

    /**
     * Prepares a server with a queue and background worker.
     * @param server The server that needs to be prepped for queueing.
     */
    private fun initQueuedServer(server: RegisteredServer) {
        if (!serverToQueue.containsKey(server)) {
            serverToQueue[server] = createQueue()
            val queue = serverToQueue[server]!!
            proxyServer.scheduler.buildTask(this) {
                try {
                    val next = queue.next()
                    if (next != null) {
                        next.server.ping()?.thenApply { serverPing: ServerPing ->
                            if (serverPing.players.isPresent && next.server.playersConnected.size + 1 <= serverPing.players.get().max) {
                                val queuePlayer = proxyServer.getPlayer(next.uuid)
                                queuePlayer.ifPresent { player ->
                                    playerToServerToSwitch[player] = server
                                    player.createConnectionRequest(next.server).connect()
                                        .thenAccept { c: ConnectionRequestBuilder.Result? ->
                                            var tries = playersToTries.getOrPut(next, {AtomicInteger(0)})
                                            if ((c != null && c.isSuccessful) || tries.get() > 2 || (c?.reasonComponent!!.isPresent && c.reasonComponent.get().toString().contains("whitelist", true))) {
                                                playerToQueue.remove(player)
                                                queue.remove(next)
                                                queue.broadcastIndexMessage(
                                                    next.server.serverInfo.name,
                                                    config.settings.intraServerQueue.oneLessPlayerInQueueMessage,
                                                    config.settings.intraServerQueue.lastPlayerInQueueMessage
                                                )
                                                player.sendMessage(
                                                    MiniMessage.get().parse(
                                                        config.settings.intraServerQueue.joinedServerMessage,
                                                        Template.of("playerName", player.username),
                                                        Template.of("destServer", next.server.serverInfo.name)
                                                    )
                                                )
                                                playersToTries.remove(next)
                                                return@thenAccept
                                            }
                                            println(tries.get())
                                            tries.incrementAndGet()
                                        }
                                }
                            }
                        }
                    }
                } catch (e: Exception) {
                }
            }.repeat(config.settings.intraServerQueue.millisecondsPerPlayer.toLong(), TimeUnit.MILLISECONDS)
                .schedule()
            proxyServer.scheduler.buildTask(this) {
                val next = queue.next()
                if (next != null) {
                    queue.broadcastIndexMessage(
                        next.server.serverInfo.name,
                        config.settings.intraServerQueue.oneLessPlayerInQueueMessage,
                        config.settings.intraServerQueue.lastPlayerInQueueMessage
                    )
                }
            }.repeat(config.settings.intraServerQueue.playerInQueueTimePerMessage, TimeUnit.MILLISECONDS)
                .schedule()
        }
    }

    /**
     * Is this server in the intraServerQueue?
     *
     * @param resultServer The server in quetion.
     */
    private fun isQueuedServer(resultServer: ServerPreConnectEvent.ServerResult): Boolean {
        if (!resultServer.server.isPresent) return false
        for (server in config.settings.intraServerQueue.queuedServers) {
            if (server == resultServer.server.get().serverInfo.name) {
                return true
            }
        }
        return false
    }

    private fun createQueue(): Queue {
        return when (config.settings.queueBackend) {
            else -> LocalQueue()
        }
    }
}