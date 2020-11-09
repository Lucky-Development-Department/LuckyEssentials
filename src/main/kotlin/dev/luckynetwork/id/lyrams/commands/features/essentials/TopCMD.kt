package dev.luckynetwork.id.lyrams.commands.features.essentials

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.Location
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TopCMD : BetterCommand("top") {

    override fun execute(
        sender: CommandSender,
        commandLabel: String,
        args: Array<String>
    ): Boolean {
        if (sender !is Player || !sender.checkPermission("top"))
            return false

        val location = sender.location
        val highest = location.world.getHighestBlockAt(location.blockX, location.blockZ)

        if (location.blockY >= highest.y) {
            sender.sendMessage(Config.prefix + " §cYou are already at the highest block!")
            return false
        }

        sender.sendMessage(Config.prefix + " §aTeleporting!")
        sender.teleport(Location(location.world, location.x, (highest.y + 1.0), location.z, location.yaw, location.pitch))
        return false
    }
}