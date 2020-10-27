package dev.luckynetwork.id.lyrams.commands.features.essentials

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SudoCMD : BetterCommand("sudo") {

    override fun execute(
        sender: CommandSender,
        commandLabel: String,
        args: Array<String>
    ): Boolean {
        if (!sender.checkPermission("sudo"))
            return false

        var target: Player
        target =
            if (sender !is Player) {
                if (args.isEmpty()) {
                    sender.sendMessage(Config.prefix + " §cInvalid usage!")
                    return false
                }
                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(Config.prefix + " §cPlayer not found!")
                    return false
                }
                Bukkit.getPlayer(args[0])

            } else
                sender

        if (args.isEmpty()) {
            sender.sendMessage(Config.prefix + " §cPlease provide a player!")
            return false
        }

        if (sender is Player) {
            if (Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(Config.prefix + " §cPlayer not found!")
                return false
            }
            target = Bukkit.getPlayer(args[0]) as Player
        }

        if (target == sender) {
            sender.sendMessage(Config.prefix + " §cYou can't sudo yourself!")
            return false
        }

        if (args.size < 2) {
            sender.sendMessage(Config.prefix + " §cInvalid usage!")
            return false
        }

        val argsAsString = args.joinToString(" ")
            .split(target.name + " ")[1]

        if (!argsAsString.startsWith("c:"))
            executeCommand(target, argsAsString)
        else
            target.chat(argsAsString.split("c:")[1])

        return false
    }

}

fun executeCommand(sender: CommandSender, command: String) {
    var toExec = command
    if (command.startsWith("/"))
        toExec = command.replaceFirst("/", "")

    Bukkit.dispatchCommand(sender, toExec)
}