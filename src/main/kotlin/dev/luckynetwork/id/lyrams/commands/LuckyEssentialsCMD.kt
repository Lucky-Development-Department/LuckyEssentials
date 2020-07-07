package dev.luckynetwork.id.lyrams.commands

import dev.luckynetwork.id.lyrams.LuckyEssentials
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class LuckyEssentialsCMD : CommandExecutor {

    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        commandName: String?,
        args: Array<out String>?
    ): Boolean {

        sender ?: return false

        sender.sendMessage(
            "§e§lLuckyEssentials §a/ §aCurrently using §eLuckyEssentials §dv" + LuckyEssentials.instance.description.version +
                    " §aby §e" +
                    LuckyEssentials.instance.description.authors.toString()
                        .replace("[", "")
                        .replace("]", "")
        )

        return false
    }

}