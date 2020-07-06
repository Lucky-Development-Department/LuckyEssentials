package dev.luckynetwork.id.lyrams.commands

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class FlyCMD : CommandExecutor {

    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        commandName: String?,
        args: Array<out String>?
    ): Boolean {

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

        if (!sender.checkPermission("fly", others))
            return false

        target.allowFlight = !target.allowFlight

        val newFlightState = target.allowFlight

        when {
            others -> {
                if (newFlightState) {
                    sender.sendMessage("§e§lLuckyNetwork §a/ §aFlight has been enabled for §l" + target.name + "§a!")
                    target.sendMessage("§e§lLuckyNetwork §a/ §aYou can now fly!")
                    return false
                }
                sender.sendMessage("§e§lLuckyNetwork §a/ §cFlight has been disabled for §l" + target.name + "§a!")
                target.sendMessage("§e§lLuckyNetwork §a/ §cYou can no longer fly!")
            }
            else -> {
                if (newFlightState) target.sendMessage("§e§lLuckyNetwork §a/ §aYou can now fly!")
                else target.sendMessage("§e§lLuckyNetwork §a/ §cYou can no longer fly!")
            }
        }

        return false

    }

}