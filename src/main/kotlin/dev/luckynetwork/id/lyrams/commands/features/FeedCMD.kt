package dev.luckynetwork.id.lyrams.commands.features

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class FeedCMD : CommandExecutor {

    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        commandName: String?,
        args: Array<out String>?
    ): Boolean {
        if (!sender!!.checkPermission("feed"))
            return false

        var target: Player

        // casts target
        target =
                // if console executes this
            if (sender !is Player) {
                // console must specify a player
                if (args!!.isEmpty()) {
                    sender.sendMessage("§e§lLuckyEssentials §a/ §cInvalid usage!")
                    return false
                }

                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage("§e§lLuckyEssentials §a/ §cPlayer not found!")
                    return false
                }

                Bukkit.getPlayer(args[0])

                // if executed by player
            } else
                sender

        var others = false

        if (args!!.isNotEmpty()) {
            if (Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage("§e§lLuckyEssentials §a/ §cPlayer not found!")
                return false
            }

            target = Bukkit.getPlayer(args[0]) as Player

            others = true
        }

        if (!sender.checkPermission("feed", others))
            return false

        target.foodLevel = 20

        when {
            others -> {
                sender.sendMessage("§e§lLuckyEssentials §a/ §a§l" + target.name + " §ahas been fed!")
                target.sendMessage("§e§lLuckyEssentials §a/ §aYou have been fed!")
            }
            else -> {
                target.sendMessage("§e§lLuckyEssentials §a/ §aYou have been fed!")
            }
        }

        return false
    }

}