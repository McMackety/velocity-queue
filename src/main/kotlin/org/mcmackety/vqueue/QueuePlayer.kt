package org.mcmackety.vqueue

import com.velocitypowered.api.proxy.server.RegisteredServer
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

data class QueuePlayer(
    val uuid: UUID,
    val server: RegisteredServer,
    val joinTime: LocalDateTime,
    val priority: Long
) {
    fun getQueueScore(currentTime: Long): Long {
        return (priority * QueuePlugin.config.settings.priorityQueueMultiplier) + (currentTime - joinTime.toEpochSecond(ZoneOffset.UTC))
    }
}