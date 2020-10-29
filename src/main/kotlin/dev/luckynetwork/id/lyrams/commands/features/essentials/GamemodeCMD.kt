package dev.luckynetwork.id.lyrams.commands.features.essentials

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GamemodeCMD : BetterCommand("gamemode", "gm", "gmc", "gms", "gmsp", "gma") {

    override fun execute(
        sender: CommandSender,
        commandLabel: String,
        args: Array<String>
    ): Boolean {
        if (!sender.checkPermission("gamemode"))
            return false

        if (commandLabel.startsWith("gm")) {
            var target: Player
            target =
                if (sender !is Player) {
                    if (args.isEmpty()) {
                        sendUsage(sender)
                        return false
                    }
                    if (Bukkit.getPlayer(args[0]) == null) {
                        sender.sendMessage(Config.prefix + " §cPlayer not found!")
                        return false
                    }
                    Bukkit.getPlayer(args[0])

                } else
                    sender

            var others = false
            if (args.isNotEmpty() && sender is Player) {
                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(Config.prefix + " §cPlayer not found!")
                    return false
                }
                target = Bukkit.getPlayer(args[0]) as Player
                others = true
            }

            if (commandLabel.split("gm")[1].isEmpty()) {
                sendUsage(sender)
                return false
            }

            val targetGamemode = when (commandLabel.split("gm")[1].toLowerCase()) {
                "s", "0" -> "survival"
                "c", "1" -> "creative"
                "a", "2" -> "adventure"
                "sp", "3" -> "spectator"
                else -> args[0].toLowerCase()
            }

            if (!sender.checkPermission("gamemode.$targetGamemode", others))
                return false

            target.gameMode = GameMode.valueOf(targetGamemode.toUpperCase())

            if (others) {
                sender.sendMessage(Config.prefix + " §a§l" + target.name + "('s) §agamemode has been updated!")
                target.sendMessage(Config.prefix + " §aYour gamemode has been updated!")
            } else {
                sender.sendMessage(Config.prefix + " §aYour gamemode has been updated!")
            }

        } else if (commandLabel.startsWith("gamemode")) {
            var target: Player
            target =
                if (sender !is Player) {
                    if (args.isEmpty()) {
                        sendUsage(sender)
                        return false
                    }
                    if (Bukkit.getPlayer(args[1]) == null) {
                        sender.sendMessage(Config.prefix + " §cPlayer not found!")
                        sender.sendMessage(Config.prefix + " §cPlayer not found!")
                        return false
                    }
                    Bukkit.getPlayer(args[1])

                } else
                    sender

            var others = false

            if (args.isEmpty()) {
                sendUsage(sender)
                return false
            }

            if (args.size == 2 && sender is Player) {
                if (Bukkit.getPlayer(args[1]) == null) {
                    sender.sendMessage(Config.prefix + " §cPlayer not found!")
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

            if (others) {
                sender.sendMessage(Config.prefix + " §a§l" + target.name + "('s) §agamemode has been updated!")
                target.sendMessage(Config.prefix + " §aYour gamemode has been updated!")
            } else {
                sender.sendMessage(Config.prefix + " §aYour gamemode has been updated!")
            }

        } else {
            sendUsage(sender)
        }

        return false
    }

}

private fun sendUsage(sender: CommandSender) {
    sender.sendMessage("§cUsage: /gamemode <mode> [player]")
    sender.sendMessage("§cUsage: /gm<mode> [player]")
}