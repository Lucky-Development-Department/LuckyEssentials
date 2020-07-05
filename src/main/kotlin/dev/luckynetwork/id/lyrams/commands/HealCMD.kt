package dev.luckynetwork.id.lyrams.commands

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
    ): Boolean
    {
        if (sender !is Player)
            return false

        var target = sender
        var others = false

        if (args!!.isNotEmpty()) {
            if (Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage("§e§lLuckyNetwork §a/ §cPlayer not found!")
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
                sender.sendMessage("§e§lLuckyNetwork §a/ §a§l" + target.name + " §ahas been healed!")
                target.sendMessage("§e§lLuckyNetwork §a/ §aYou have been healed!")
            }
            else -> {
                target.sendMessage("§e§lLuckyNetwork §a/ §aYou have been healed!")
            }
        }

        return false

    }

}