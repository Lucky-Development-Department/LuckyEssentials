package dev.luckynetwork.id.lyrams.commands.features

import dev.luckynetwork.id.lyrams.extensions.asString
import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.extensions.checkPermissionSilent
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class KickAllCMD : CommandExecutor {

    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        commandName: String?,
        args: Array<out String>?
    ): Boolean {
        if (!sender!!.checkPermission("kickall"))
            return false

        val reason =
            if (args!!.isNotEmpty())
                args.asString()
            else
                "Kicked by a staff member"

        for (online in Bukkit.getOnlinePlayers()) {
            if (sender !is Player || !online.name.equals(sender.name, true)) {
                if (!online.checkPermissionSilent("kickall.exempt")) {
                    online.kickPlayer(reason)
                }
            }
        }

        sender.sendMessage("§e§lLuckyEssentials §a/ §aKicked all players!")

        return false
    }
}