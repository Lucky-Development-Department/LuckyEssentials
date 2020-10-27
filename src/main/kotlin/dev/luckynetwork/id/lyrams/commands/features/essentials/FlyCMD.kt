package dev.luckynetwork.id.lyrams.commands.features.essentials

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.extensions.getTargetPlayer
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class FlyCMD : BetterCommand {

    override fun execute(sender: CommandSender, args: Array<String>) {
        if (!sender.checkPermission("fly"))
            return

        val targets = args.getTargetPlayer(sender, 0)
        val flightMap = HashMap<Player, Boolean>()

        if (targets.isEmpty())
            return

        val others = !targets.contains(sender) || targets.size > 1

        if (!sender.checkPermission("fly", others))
            return

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

    }

}