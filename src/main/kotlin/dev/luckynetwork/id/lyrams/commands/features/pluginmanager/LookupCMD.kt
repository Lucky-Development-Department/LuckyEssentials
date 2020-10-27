package dev.luckynetwork.id.lyrams.commands.features.pluginmanager

import com.google.common.base.Joiner
import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.objects.PluginUtils
import dev.luckynetwork.id.lyrams.utils.SubCommand
import org.bukkit.command.CommandSender

class LookupCMD(name: String, vararg aliases: String) : SubCommand(name, *aliases) {

    override fun execute(sender: CommandSender, args: Array<out String>) {
        if (!sender.checkPermission("pluginmanager.lookup"))
            return

        if (args.isEmpty())
            return sender.sendMessage(Config.prefix + " §cPlease provide a plugin!")

        val commandName = args[0].replaceFirst("/", "")
        val plugins = PluginUtils.findByCommand(commandName)
        if (plugins.isEmpty())
            return sender.sendMessage(Config.prefix + " §cNo plugins found!")

        sender.sendMessage(Config.prefix + " §a/$commandName is registered to ${Joiner.on(", ").join(plugins)}")

    }

}