package dev.luckynetwork.id.lyrams.commands.features.pluginmanager

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.objects.PluginUtils
import dev.luckynetwork.id.lyrams.utils.SubCommand
import org.bukkit.command.CommandSender

class LoadCMD(name: String, vararg aliases: String) : SubCommand(name, *aliases) {

    override fun execute(sender: CommandSender, args: Array<out String>) {
        if (!sender.checkPermission("pluginmanager.load"))
            return
        if (args.isEmpty())
            return sender.sendMessage(Config.prefix + " §cPlease provide a plugin!")

        val plugin = PluginUtils.getPluginByName(args[0])
        if (plugin != null)
            return sender.sendMessage(Config.prefix + " §c§l${args[0]} §cis already loaded!")

        if (PluginUtils.load(args[0]))
            sender.sendMessage(Config.prefix + " §a§l${args[0]} §aloaded!")
        else
            sender.sendMessage(Config.prefix + " §cFailed loading §c§l${args[0]} §c! §7Check console for more details!")

    }

}