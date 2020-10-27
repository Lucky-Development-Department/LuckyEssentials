package dev.luckynetwork.id.lyrams.commands.features.essentials

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GetPosCMD : BetterCommand {

    override fun execute(sender: CommandSender, args: Array<String>) {
        if (!sender.checkPermission("getpos"))
            return

        var target: Player
        target =
                // if console executes this
            if (sender !is Player) {
                // console must specify a player
                if (args.isEmpty())
                    return sender.sendMessage(Config.prefix + " §cInvalid usage!")

                if (Bukkit.getPlayer(args[0]) == null)
                    return sender.sendMessage(Config.prefix + " §cPlayer not found!")

                Bukkit.getPlayer(args[0])
            } else
                sender

        var others = false

        if (args.isNotEmpty() && sender is Player) {

            if (Bukkit.getPlayer(args[0]) == null)
                return sender.sendMessage(Config.prefix + " §cPlayer not found!")

            target = Bukkit.getPlayer(args[0]) as Player
            others = true

        }

        if (!sender.checkPermission("getpos", others))
            return

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
    }
}