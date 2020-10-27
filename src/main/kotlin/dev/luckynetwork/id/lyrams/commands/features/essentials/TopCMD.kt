package dev.luckynetwork.id.lyrams.commands.features.essentials

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TopCMD : BetterCommand {

    override fun execute(sender: CommandSender, args: Array<String>) {
        if (sender !is Player || !sender.checkPermission("top"))
            return

        sender.teleport(getHighestLocation(sender.location))
        sender.sendMessage(Config.prefix + " §aTeleporting!")
    }
}

fun getHighestLocation(location: Location): Location {
    var i = 255.0
    while (location.world.getBlockAt(Location(location.world, location.x, i, location.z)).type == Material.AIR)
        i--

    return Location(location.world, location.x, i + 1, location.z)
}