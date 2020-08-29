package dev.luckynetwork.id.lyrams.utils

import org.bukkit.command.CommandSender

abstract class SubCommand(val name: String, vararg val aliases: String) {

    abstract fun execute(sender: CommandSender, args: Array<out String>)

}