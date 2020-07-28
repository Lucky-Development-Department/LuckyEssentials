package dev.luckynetwork.id.lyrams.commands

import dev.luckynetwork.id.lyrams.LuckyEssentials
import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.objects.Config
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

        val plugin = LuckyEssentials.instance

        if (args!!.isEmpty()) {
            sender.sendMessage(
                Config.prefix + " §aCurrently using §eLuckyEssentials §dv" + plugin.description.version +
                        " §aby §e" +
                        plugin.description.authors.toString()
                            .replace("[", "")
                            .replace("]", "")
            )
            return false
        }

        if (args[0].equals("reload", true)) {

            if (!sender.checkPermission("reload"))
                return false

            Config.reloadAll()

            sender.sendMessage(Config.prefix + " §aConfig Reloaded!")

        }

        return false
        
    }

}