package org.mcmackety.vqueue

import com.velocitypowered.api.command.SimpleCommand
import com.velocitypowered.api.proxy.Player
import net.kyori.adventure.text.format.NamedTextColor


class LeaveQueue(val queuePlugin: QueuePlugin) : SimpleCommand {
    override fun execute(invocation: SimpleCommand.Invocation) {
        val source = invocation.source()
        if (source is Player) {
            val leftQueue = queuePlugin.leaveQueue(source)
        }
    }
}