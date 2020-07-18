package dev.luckynetwork.id.lyrams.commands

import dev.luckynetwork.id.lyrams.LuckyEssentials
import dev.luckynetwork.id.lyrams.objects.Slots
import dev.luckynetwork.id.lyrams.objects.Whitelist
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import java.io.File

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
                LuckyEssentials.prefix + " §aCurrently using §eLuckyEssentials §dv" + plugin.description.version +
                        " §aby §e" +
                        plugin.description.authors.toString()
                            .replace("[", "")
                            .replace("]", "")
            )
            return false
        }

        if (args[0].equals("reload", true)) {
            val file = File(LuckyEssentials.instance.dataFolder, "config.yml")

            if (!file.exists())
                plugin.saveResource("config.yml", false)

            plugin.reloadConfig()

            LuckyEssentials.prefix = ChatColor.translateAlternateColorCodes(
                '&',
                plugin.config.getString("prefix", "&e&lLuckyEssentials &a/")
            )

            sender.sendMessage(LuckyEssentials.prefix + " §aConfig Reloaded!")

            Slots.reload()
            sender.sendMessage(LuckyEssentials.prefix + " §aSlots Config Reloaded!")
            Whitelist.reload()
            sender.sendMessage(LuckyEssentials.prefix + " §aWhitelist Config Reloaded!")

        }

        return false
    }

}