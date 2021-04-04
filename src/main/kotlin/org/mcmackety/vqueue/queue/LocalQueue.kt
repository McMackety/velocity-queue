package org.mcmackety.vqueue.queue

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.Template
import org.mcmackety.vqueue.QueuePlayer
import org.mcmackety.vqueue.QueuePlugin
import sun.awt.Mutex
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*


class LocalQueue : Queue {
    private val players: MutableList<QueuePlayer> = mutableListOf()
    private var lastTimePlayerLeft: LocalDateTime = LocalDateTime.MIN
    private var timesForPlayersToLeave: MutableList<Long> = mutableListOf()
    private var mutex: Mutex = Mutex()

    override fun add(uuid: QueuePlayer) {
        mutex.lock()
        if (players.contains(uuid)) return
        players.add(uuid)
        reorderPlayers()
        mutex.unlock()
    }

    override fun get(uuid: UUID): QueuePlayer? {
        mutex.lock()
        for (player in players) {
            if (player.uuid == uuid) {
                mutex.unlock()
                return player
            }
        }
        mutex.unlock()
        return null
    }

    override fun remove(uuid: QueuePlayer) {
        mutex.lock()
        val now = LocalDateTime.now()
        if (lastTimePlayerLeft != LocalDateTime.MIN) {
            val newVal = now.toEpochSecond(ZoneOffset.UTC) - lastTimePlayerLeft.toEpochSecond(ZoneOffset.UTC)
            timesForPlayersToLeave.add(newVal)
            timesForPlayersToLeave = timesForPlayersToLeave.take(10).toMutableList()
        }
        lastTimePlayerLeft = now

        players.remove(uuid)
        mutex.unlock()
    }

    override fun removeUUID(uuid: UUID) {
        mutex.lock()
        val now = LocalDateTime.now()
        if (lastTimePlayerLeft != LocalDateTime.MIN) {
            val newVal = now.toEpochSecond(ZoneOffset.UTC) - lastTimePlayerLeft.toEpochSecond(ZoneOffset.UTC)
            timesForPlayersToLeave.add(newVal)
            timesForPlayersToLeave = timesForPlayersToLeave.take(10).toMutableList()
        }
        lastTimePlayerLeft = now

        for (player in players) {
            if (player.uuid == uuid) {
                players.remove(player)
                mutex.unlock()
                return
            }
        }
        mutex.unlock()
    }

    override fun next(): QueuePlayer? {
        mutex.lock()
        reorderPlayers()
        return if (players.size > 0) {
            val player = players[0]
            mutex.unlock()
            player
        } else {
            mutex.unlock()
            null
        }
    }

    override fun contains(uuid: UUID): Boolean {
        mutex.lock()
        for (player in players) {
            if (player.uuid == uuid) {
                mutex.unlock()
                return true
            }
        }
        mutex.unlock()
        return false
    }

    override fun broadcastIndexMessage(destinationServer: String, inQueue: String, nextInQueue: String) {
        mutex.lock()
        reorderPlayers()
        for (player in players) {
            QueuePlugin.proxyServer.getPlayer(player.uuid).ifPresent { proxyPlayer ->
                val index = players.indexOf(player)
                val nameTemplate = Template.of("playerName", proxyPlayer.username)
                val destTemplate = Template.of("destServer", destinationServer)

                var durationString = "Computing..."
                if (timesForPlayersToLeave.size >= 10) {
                    durationString = Duration.ofSeconds(timesForPlayersToLeave.average().toLong()).toString()
                        .substring(2)
                        .replace("(\\d[HMS])(?!$)".toRegex(), "$1 ")
                        .toLowerCase();
                }
                val timeTemplate = Template.of("estimatedTime", durationString)
                if (index > 0) {
                    val indexTemplate = Template.of("index", (players.indexOf(player) + 1).toString())
                    val sizeTemplate = Template.of("size", players.size.toString())
                    proxyPlayer.sendMessage(MiniMessage.get().parse(inQueue, nameTemplate, indexTemplate, destTemplate, timeTemplate, sizeTemplate))
                } else if (index == 0) {
                    proxyPlayer.sendMessage(MiniMessage.get().parse(nextInQueue, nameTemplate, destTemplate, timeTemplate))
                }
            }
        }
        mutex.unlock()
    }

    private fun reorderPlayers() {
        val currentTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
        players.sortByDescending {
            QueuePlugin.proxyServer.getPlayer(it.uuid).ifPresent { aPlayer ->
                if (aPlayer.hasPermission("velocityqueue.debug")) {
                    aPlayer.sendMessage(Component.text(it.getQueueScore(currentTime)))
                }
            }
            it.getQueueScore(currentTime)
        }
    }
}