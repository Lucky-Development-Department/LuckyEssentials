package dev.luckynetwork.id.lyrams.commands

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GamemodeCMD : CommandExecutor {

    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        commandName: String?,
        args: Array<out String>?
    ): Boolean {
        if (sender !is Player)
            return false

        if (!sender.checkPermission("gamemode"))
            return false

        commandName ?: return false

        if (commandName.startsWith("gm")) {
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

            val targetGamemode = when (commandName.split("gm")[1].toLowerCase()) {
                "s", "0" -> "survival"
                "c", "1" -> "creative"
                "a", "2" -> "adventure"
                "sp", "3" -> "spectator"
                else -> args[0].toLowerCase()
            }

            if (!sender.checkPermission("gamemode.$targetGamemode", others))
                return false

            target.gameMode = GameMode.valueOf(targetGamemode.toUpperCase())

            // todo: maybe implement a logging feature
            if (others) {
                sender.sendMessage("§e§lLuckyNetwork §a/ §a§l" + target.name + "('s) §agamemode has been updated!")
                target.sendMessage("§e§lLuckyNetwork §a/ §aYour gamemode has been updated!")
            } else {
                sender.sendMessage("§e§lLuckyNetwork §a/ §aYour gamemode has been updated!")
            }

        } else if (commandName.startsWith("gamemode")) {
            var target = sender
            var others = false

            if (args!!.isEmpty()) {
                sendUsage(sender)
                return false
            }

            if (args.size == 2) {
                if (Bukkit.getPlayer(args[1]) == null) {
                    sender.sendMessage("§e§lLuckyNetwork §a/ §cPlayer not found!")
                    return false
                }

                target = Bukkit.getPlayer(args[1]) as Player

                others = true
            }

            val targetGamemode = when (args[0].toLowerCase()) {
                "s", "0" -> "survival"
                "c", "1" -> "creative"
                "a", "2" -> "adventure"
                "sp", "3" -> "spectator"
                else -> args[0].toLowerCase()
            }

            if (!sender.checkPermission("gamemode.$targetGamemode", others))
                return false

            try {
                target.gameMode = GameMode.valueOf(targetGamemode.toUpperCase())
            } catch (ignored: Exception) {
                sendUsage(sender)
                return false
            }

            // todo: maybe implement a logging feature
            if (others) {
                sender.sendMessage("§e§lLuckyNetwork §a/ §a§l" + target.name + "('s) §agamemode has been updated!")
                target.sendMessage("§e§lLuckyNetwork §a/ §aYour gamemode has been updated!")
            } else {
                sender.sendMessage("§e§lLuckyNetwork §a/ §aYour gamemode has been updated!")
            }

        } else {
            sendUsage(sender)
        }

        return false
    }

}


private fun sendUsage(player: Player) {
    player.sendMessage("§cUsage: /gamemode <mode> [player]")
    player.sendMessage("§cUsage: /gm<mode> [player]")
}