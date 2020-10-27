package dev.luckynetwork.id.lyrams.commands.features.pluginmanager

import com.google.common.base.Joiner
import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.extensions.colorizeTrueOrFalse
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.objects.PluginUtils
import dev.luckynetwork.id.lyrams.utils.SubCommand
import org.bukkit.command.CommandSender

class InfoCMD(name: String, vararg aliases: String) : SubCommand(name, *aliases) {

    override fun execute(sender: CommandSender, args: Array<out String>) {
        if (!sender.checkPermission("pluginmanager.info"))
            return

        if (args.isEmpty())
            return sender.sendMessage(Config.prefix + " §cPlease provide a plugin!")

        val plugin =
            PluginUtils.getPluginByName(args[0]) ?: return sender.sendMessage(Config.prefix + " §cPlugin not found!")

        sender.sendMessage("§7Plugin Information: §a${plugin.name}")
        sender.sendMessage("§7- Version: §a${plugin.description.version}")
        sender.sendMessage("§7- Author(s): §a${Joiner.on(", ").join(plugin.description.authors)}")
        sender.sendMessage("§7- State: ${plugin.isEnabled.toString().colorizeTrueOrFalse()}")
        sender.sendMessage("§7- Main: ${plugin.description.main}")
        if (plugin.description.depend.isNotEmpty())
            sender.sendMessage("§7- Depend(s): §a${Joiner.on(", ").join(plugin.description.depend)}")
        if (plugin.description.softDepend.isNotEmpty())
            sender.sendMessage("§7- SoftDepends(s): §a${Joiner.on(", ").join(plugin.description.softDepend)}")

    }

}