package dev.luckynetwork.id.lyrams.commands.features.essentials

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SudoCMD : BetterCommand {

    override fun execute(sender: CommandSender, args: Array<String>) {
        if (!sender.checkPermission("sudo"))
            return

        var target: Player
        target =
            if (sender !is Player) {
                // console must specify a player
                if (args.isEmpty())
                    return sender.sendMessage(Config.prefix + " §cInvalid usage!")

                if (Bukkit.getPlayer(args[0]) == null)
                    return sender.sendMessage(Config.prefix + " §cPlayer not found!")

                Bukkit.getPlayer(args[0])

                // if executed by player
            } else
                sender

        if (args.isNotEmpty() && sender is Player) {
            if (Bukkit.getPlayer(args[0]) == null)
                return target.sendMessage(Config.prefix + " §cPlayer not found!")

            target = Bukkit.getPlayer(args[0])
        }

        if (target == sender)
            return sender.sendMessage(Config.prefix + " §cYou can't sudo yourself!")

        if (args.size < 2)
            return sender.sendMessage(Config.prefix + " §cInvalid usage!")

        val argsAsString = args.joinToString(" ")
            .split(target.name + " ")[1]

        if (!argsAsString.startsWith("c:")) executeCommand(target, argsAsString)
        else target.chat(argsAsString.split("c:")[1])

    }

}

fun executeCommand(sender: CommandSender, command: String) {
    var toExec = command
    if (command.startsWith("/"))
        toExec = command.replaceFirst("/", "")

    Bukkit.dispatchCommand(sender, toExec)
}