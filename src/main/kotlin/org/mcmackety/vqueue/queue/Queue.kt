package org.mcmackety.vqueue.queue

import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.Template
import org.mcmackety.vqueue.QueuePlayer
import org.mcmackety.vqueue.QueuePlugin
import java.util.*


interface Queue {

    fun add(uuid: QueuePlayer)

    fun remove(uuid: QueuePlayer)

    fun removeUUID(uuid: UUID)

    fun next(): QueuePlayer?

    fun contains(uuid: UUID): Boolean

    fun broadcastIndexMessage(destinationServer: String, inQueue: String, nextInQueue: String)
}