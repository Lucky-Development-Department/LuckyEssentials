package dev.luckynetwork.id.lyrams.commands.features.essentials

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.extensions.checkPermissionSilent
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class KickAllCMD : BetterCommand {

    override fun execute(sender: CommandSender, args: Array<String>) {
        if (!sender.checkPermission("kickall"))
            return

        var ignoreStaff = false
        if (args.isNotEmpty() && args[0] == "**")
            ignoreStaff = true

        val reason =
            if (ignoreStaff)
                if (args.size > 1)
                    args.joinToString("** ")
                else
                    "Kicked by a staff member"
            else
                if (args.isNotEmpty())
                    args.joinToString(" ")
                else
                    "Kicked by a staff member"

        for (online in Bukkit.getOnlinePlayers()) {
            if (sender !is Player || !online.name.equals(sender.name, true)) {
                if (online.checkPermissionSilent("kickall.exempt") && !ignoreStaff) {
                    continue
                }
                online.kickPlayer(reason)
            }
        }

        sender.sendMessage(Config.prefix + " §aKicked all players!")

    }

}