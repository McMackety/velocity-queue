package org.mcmackety.vqueue

import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.Template
import java.util.*


class Queue {
    private val players: MutableList<QueuePlayer> = mutableListOf()

    fun add(uuid: QueuePlayer) {
        if (players.contains(uuid)) return
        players.add(uuid)
    }

    fun remove(uuid: QueuePlayer) {
        players.remove(uuid)
    }

    fun removeUUID(uuid: UUID) {
        for (player in players) {
            if (player.uuid == uuid) {
                players.remove(player)
                return
            }
        }
    }

    fun placeInQueue(uuid: QueuePlayer): Int {
        return players.indexOf(uuid)
    }

    fun next(): QueuePlayer? {
        return if (players.size > 0) {
            val player = players[0]
            player
        } else {
            null
        }
    }

    fun broadcastIndexMessage(inQueue: String, nextInQueue: String) {
        for (player in players) {
            QueuePlugin.proxyServer.getPlayer(player.uuid).ifPresent { proxyPlayer ->
                val index = players.indexOf(player)
                val nameTemplate = Template.of("playerName", proxyPlayer.username)
                if (index > 0) {
                    val indexTemplate = Template.of("index", (players.indexOf(player) + 1).toString())
                    proxyPlayer.sendMessage(MiniMessage.get().parse(inQueue, nameTemplate, indexTemplate))
                } else if (index == 0) {
                    proxyPlayer.sendMessage(MiniMessage.get().parse(nextInQueue, nameTemplate))
                }
            }
        }

    }
}