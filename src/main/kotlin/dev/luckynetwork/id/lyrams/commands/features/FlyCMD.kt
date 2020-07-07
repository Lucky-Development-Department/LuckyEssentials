package dev.luckynetwork.id.lyrams.commands.features

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
        if (!sender!!.checkPermission("fly"))
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

        if (args!!.isNotEmpty() && sender !is Player) {
            if (Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage("§e§lLuckyEssentials §a/ §cPlayer not found!")
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
                    sender.sendMessage("§e§lLuckyEssentials §a/ §aFlight has been enabled for §l" + target.name + "§a!")
                    target.sendMessage("§e§lLuckyEssentials §a/ §aYou can now fly!")
                    return false
                }
                sender.sendMessage("§e§lLuckyEssentials §a/ §cFlight has been disabled for §l" + target.name + "§a!")
                target.sendMessage("§e§lLuckyEssentials §a/ §cYou can no longer fly!")
            }
            else -> {
                if (newFlightState) target.sendMessage("§e§lLuckyEssentials §a/ §aYou can now fly!")
                else target.sendMessage("§e§lLuckyEssentials §a/ §cYou can no longer fly!")
            }
        }

        return false

    }

}