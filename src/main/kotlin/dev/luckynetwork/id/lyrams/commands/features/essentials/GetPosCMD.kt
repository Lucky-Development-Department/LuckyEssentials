package dev.luckynetwork.id.lyrams.commands.features.essentials

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GetPosCMD : BetterCommand("getpos") {

    override fun execute(
        sender: CommandSender,
        commandLabel: String,
        args: Array<String>
    ): Boolean {
        if (!sender.checkPermission("getpos"))
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

        var others = false
        if (args.isNotEmpty() && sender is Player) {
            if (Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(Config.prefix + " §cPlayer not found!")
                return false
            }
            target = Bukkit.getPlayer(args[0]) as Player
            others = true
        }

        if (!sender.checkPermission("getpos", others))
            return false

        val loc = target.location
        sender.sendMessage("§6${target.name}('s) location:")
        sender.sendMessage("§6X: ${(loc.x)}")
        sender.sendMessage("§6Y: ${(loc.y)}")
        sender.sendMessage("§6Z: ${(loc.z)}")
        sender.sendMessage("§6Yaw: ${(loc.yaw)}")
        sender.sendMessage("§6Pitch: ${(loc.pitch)}")
        sender.sendMessage("§6World: ${(loc.world.name)}")
        sender.sendMessage(" ")
        if (sender is Player && sender.location.world == loc.world)
            sender.sendMessage("§6Distance: ${loc.distanceSquared(sender.location)}")

        return false
    }
}