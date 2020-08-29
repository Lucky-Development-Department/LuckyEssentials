package dev.luckynetwork.id.lyrams.commands.features.essentials

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.extensions.getTargetPlayer
import dev.luckynetwork.id.lyrams.objects.Config
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class FlyCMD : CommandExecutor {

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        commandName: String,
        args: Array<out String>
    ): Boolean {
        if (!sender.checkPermission("fly"))
            return false

        val targets = args.getTargetPlayer(sender, 0)
        val flightMap = HashMap<Player, Boolean>()

        if (targets.isEmpty())
            return false

        val others = !targets.contains(sender) || targets.size > 1

        if (!sender.checkPermission("fly", others))
            return false

        targets.forEach {
            it.allowFlight = !it.allowFlight

            val flightState = it.allowFlight
            flightMap[it] = flightState

            if (flightState)
                it.sendMessage(Config.prefix + " §aYou can now fly!")
            else
                it.sendMessage(Config.prefix + " §cYou can no longer fly!")
        }

        if (others) {
            if (targets.size < 3)

                for ((key, value) in flightMap) {
                    if (value)
                        sender.sendMessage(Config.prefix + " §aFlight has been enabled for §l" + key.name + "§a!")
                    else
                        sender.sendMessage(Config.prefix + " §cFlight has been disabled for §l" + key.name + "§a!")
                }
            else

                sender.sendMessage(Config.prefix + " §cToggled flight for §l" + targets.size + " players§a!")

        }

        return false

    }

}