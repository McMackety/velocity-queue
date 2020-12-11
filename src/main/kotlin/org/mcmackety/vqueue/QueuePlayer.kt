package org.mcmackety.vqueue

import com.velocitypowered.api.proxy.server.RegisteredServer
import java.util.*

data class QueuePlayer(
    val uuid: UUID,
    val server: RegisteredServer
)