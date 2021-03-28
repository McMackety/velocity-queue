package org.mcmackety.vqueue.queue

import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.Template
import org.mcmackety.vqueue.QueuePlayer
import org.mcmackety.vqueue.QueuePlugin
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*


class LocalQueue : Queue {
    private val players: MutableList<QueuePlayer> = mutableListOf()

    override fun add(uuid: QueuePlayer) {
        if (players.contains(uuid)) return
        players.add(uuid)
    }

    override fun get(uuid: UUID): QueuePlayer? {
        for (player in players) {
            if (player.uuid == uuid) {
                return player
            }
        }
        return null
    }

    override fun remove(uuid: QueuePlayer) {
        players.remove(uuid)
    }

    override fun removeUUID(uuid: UUID) {
        for (player in players) {
            if (player.uuid == uuid) {
                players.remove(player)
                return
            }
        }
    }

    override fun next(): QueuePlayer? {
        reorderPlayers()
        return if (players.size > 0) {
            val player = players[0]
            player
        } else {
            null
        }
    }

    override fun contains(uuid: UUID): Boolean {
        for (player in players) {
            if (player.uuid == uuid) {
                return true
            }
        }
        return false
    }

    override fun broadcastIndexMessage(destinationServer: String, inQueue: String, nextInQueue: String) {
        reorderPlayers()
        for (player in players) {
            QueuePlugin.proxyServer.getPlayer(player.uuid).ifPresent { proxyPlayer ->
                val index = players.indexOf(player)
                val nameTemplate = Template.of("playerName", proxyPlayer.username)
                val destTemplate = Template.of("destServer", destinationServer)
                if (index > 0) {
                    val indexTemplate = Template.of("index", (players.indexOf(player) + 1).toString())
                    proxyPlayer.sendMessage(MiniMessage.get().parse(inQueue, nameTemplate, indexTemplate, destTemplate))
                } else if (index == 0) {
                    proxyPlayer.sendMessage(MiniMessage.get().parse(nextInQueue, nameTemplate, destTemplate))
                }
            }
        }
    }

    private fun reorderPlayers() {
        val currentTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
        players.sortByDescending {
            it.getQueueScore(currentTime)
        }
    }
}