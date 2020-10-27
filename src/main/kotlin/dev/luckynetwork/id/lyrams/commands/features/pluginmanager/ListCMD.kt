package dev.luckynetwork.id.lyrams.commands.features.pluginmanager

import com.google.common.base.Joiner
import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.objects.PluginUtils
import dev.luckynetwork.id.lyrams.utils.SubCommand
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender

class ListCMD(name: String, vararg aliases: String) : SubCommand(name, *aliases) {

    override fun execute(sender: CommandSender, args: Array<out String>) {
        if (!sender.checkPermission("pluginmanager.list"))
            return

        val includeVersion = args.isNotEmpty() && args[0] == "-v"
        val pluginList = ArrayList<String>()

        for (plugin in Bukkit.getPluginManager().plugins)
            pluginList.add(PluginUtils.getFormattedName(plugin, includeVersion))

        pluginList.sortWith(String.CASE_INSENSITIVE_ORDER)

        val plugins = Joiner.on(", ").join(pluginList)
        sender.sendMessage(Config.prefix + " §7Plugins (${pluginList.size}): $plugins")
    }

}