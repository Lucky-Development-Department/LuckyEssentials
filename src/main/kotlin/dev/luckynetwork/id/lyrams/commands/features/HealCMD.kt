package dev.luckynetwork.id.lyrams.commands.features

import dev.luckynetwork.id.lyrams.LuckyEssentials
import dev.luckynetwork.id.lyrams.extensions.checkPermission
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class HealCMD : CommandExecutor {

    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        commandName: String?,
        args: Array<out String>?
    ): Boolean {
        if (!sender!!.checkPermission("heal"))
            return false

        var target: Player

        // casts target
        target =
                // if console executes this
            if (sender !is Player) {
                // console must specify a player
                if (args!!.isEmpty()) {
                    sender.sendMessage(LuckyEssentials.prefix + " §cInvalid usage!")
                    return false
                }

                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(LuckyEssentials.prefix + " §cPlayer not found!")
                    return false
                }

                Bukkit.getPlayer(args[0])

                // if executed by player
            } else
                sender

        var others = false

        if (args!!.isNotEmpty() && sender !is Player) {
            if (Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(LuckyEssentials.prefix + " §cPlayer not found!")
                return false
            }

            target = Bukkit.getPlayer(args[0]) as Player

            others = true
        }

        if (!sender.checkPermission("heal", others))
            return false

        target.health = 20.0
        target.foodLevel = 20

        when {
            others -> {
                sender.sendMessage(LuckyEssentials.prefix + " §a§l" + target.name + " §ahas been healed!")
                target.sendMessage(LuckyEssentials.prefix + " §aYou have been healed!")
            }
            else -> {
                target.sendMessage(LuckyEssentials.prefix + " §aYou have been healed!")
            }
        }

        return false

    }

}