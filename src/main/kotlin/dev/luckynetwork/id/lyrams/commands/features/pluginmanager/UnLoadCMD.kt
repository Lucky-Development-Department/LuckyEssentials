package dev.luckynetwork.id.lyrams.commands.features.pluginmanager

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.objects.PluginUtils
import dev.luckynetwork.id.lyrams.utils.SubCommand
import org.bukkit.command.CommandSender

class UnLoadCMD(name: String, vararg aliases: String) : SubCommand(name, *aliases) {

    override fun execute(sender: CommandSender, args: Array<out String>) {
        if (!sender.checkPermission("pluginmanager.unload"))
            return

        if (args.isEmpty()) {
            sender.sendMessage(Config.prefix + " §cPlease provide a plugin!")
            return
        }

        val plugin = PluginUtils.getPluginByName(args[0])

        if (plugin == null) {
            sender.sendMessage(Config.prefix + " §c§l${args[0]} §cis already unloaded/not found!")
            return
        }

        if (PluginUtils.unload(plugin))
            sender.sendMessage(Config.prefix + " §a§l${args[0]} §aunloaded!")
        else
            sender.sendMessage(Config.prefix + " §cFailed unloading §c§l${args[0]} §c! §7Check console for more details!")

    }

}