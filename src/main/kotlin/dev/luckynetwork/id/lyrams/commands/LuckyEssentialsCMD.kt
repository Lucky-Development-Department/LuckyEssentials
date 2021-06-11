package dev.luckynetwork.id.lyrams.commands

import com.google.common.base.Joiner
import dev.luckynetwork.id.lyrams.LuckyEssentials
import dev.luckynetwork.id.lyrams.commands.features.pluginmanager.*
import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import dev.luckynetwork.id.lyrams.utils.SubCommand
import org.bukkit.command.CommandSender
import java.util.concurrent.CompletableFuture

class LuckyEssentialsCMD : BetterCommand("luckyessentials", "less") {

    private var subCommands: List<SubCommand> = listOf(
        DisableCMD("disable", "dis"),
        EnableCMD("enable", "e"),
        InfoCMD("info", "i"),
        ListCMD("list", "l"),
        LookupCMD("lookup"),
        LoadCMD("load"),
        ReloadCMD("reload", "rl"),
        RestartCMD("restart", "rs"),
        UnLoadCMD("unload"),
        UsageCMD("usage"),
    )

    override fun execute(
        sender: CommandSender,
        commandName: String,
        args: Array<String>
    ): Boolean {
        val plugin = LuckyEssentials.instance

        if (args.isEmpty()) {
            sender.sendMessage(
                Config.prefix + " §aCurrently using §eLuckyEssentials §dv" + plugin.description.version +
                        " §aby §e" + Joiner.on(", ").join(plugin.description.authors)
            )
            return false
        } else if (args[0].equals("pluginmanager", true) || args[0].equals("pm", true)) {
            if (!sender.checkPermission("pluginmanager"))
                return false

            if (args.size < 2) {
                sender.sendMessage("§cUsage: /less pm disable <plugin>")
                sender.sendMessage("§cUsage: /less pm enable <plugin>")
                sender.sendMessage("§cUsage: /less pm info <plugin>")
                sender.sendMessage("§cUsage: /less pm list [-v]")
                sender.sendMessage("§cUsage: /less pm load <plugin>")
                sender.sendMessage("§cUsage: /less pm lookup <command>")
                sender.sendMessage("§cUsage: /less pm reload <plugin>")
                sender.sendMessage("§cUsage: /less pm restart <plugin>")
                sender.sendMessage("§cUsage: /less pm unload <plugin>")
                sender.sendMessage("§cUsage: /less pm usage <plugin>")
                return false
            }

            val subCmd = args[1].lowercase()
            val newArgs = args.copyOfRange(2, args.size)
            CompletableFuture.runAsync {
                try {
                    for (subCommand in subCommands) {
                        if (subCommand.name == subCmd || subCommand.aliases.contains(subCmd)) {
                            subCommand.execute(sender, newArgs)
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            return false
        } else if (args[0].equals("reload", true)) {
            if (!sender.checkPermission("reload"))
                return false

            Config.reloadAll()
            sender.sendMessage(Config.prefix + " §aConfig Reloaded!")

            return false
        }

        sender.sendMessage(
            Config.prefix + " §aCurrently using §eLuckyEssentials §dv" + plugin.description.version +
                    " §aby §e" + Joiner.on(", ").join(plugin.description.authors)
        )

        return false
    }

}