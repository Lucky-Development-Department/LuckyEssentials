package dev.luckynetwork.id.lyrams.utils

import dev.luckynetwork.id.lyrams.objects.PluginUtils
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.util.StringUtil
import java.util.*

class PluginsTabCompleter : TabCompleter {

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        arg: String,
        args: Array<out String>
    ): MutableList<String>? {
        if (args.isEmpty())
            return null

        if (args[0].equals("pm", true) || args[0].equals("pluginmanager", true)) {
            val completions = ArrayList<String>()
            if (args.size == 3) {
                val partialPlugin = args[2]
                val pluginNames = PluginUtils.getPluginNames(false)
                StringUtil.copyPartialMatches(partialPlugin, pluginNames, completions)
            }

            completions.sort()
            return completions
        }

        return null

    }

}