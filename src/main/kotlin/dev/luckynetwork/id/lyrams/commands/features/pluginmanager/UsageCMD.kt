package dev.luckynetwork.id.lyrams.commands.features.pluginmanager

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.objects.PluginUtils
import dev.luckynetwork.id.lyrams.utils.SubCommand
import org.bukkit.command.CommandSender

class UsageCMD(name: String, vararg aliases: String) : SubCommand(name, *aliases) {

    override fun execute(sender: CommandSender, args: Array<out String>) {
        if (!sender.checkPermission("pluginmanager.usage"))
            return

        if (args.isEmpty())
            return sender.sendMessage(Config.prefix + " §cPlease provide a plugin!")

        val plugin =
            PluginUtils.getPluginByName(args[0]) ?: return sender.sendMessage(Config.prefix + " §cPlugin not found!")
        val usages = PluginUtils.getUsages(plugin)

        sender.sendMessage(Config.prefix + " §aCommands: §7$usages")

    }

}