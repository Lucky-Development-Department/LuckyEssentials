package dev.luckynetwork.id.lyrams.utils

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

interface BetterCommand : CommandExecutor {

    fun execute(sender: CommandSender, args: Array<String>)

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        this.execute(sender, args)
        return true
    }

}