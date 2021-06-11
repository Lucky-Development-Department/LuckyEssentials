package dev.luckynetwork.id.lyrams.commands.features.essentials

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SpeedCMD : BetterCommand("speed") {

    override fun execute(
        sender: CommandSender,
        commandLabel: String,
        args: Array<String>
    ): Boolean {
        if (sender !is Player || !sender.checkPermission("speed"))
            return false

        var target: Player = sender
        var others = false
        var speed: Float
        var type =
            if (target.isFlying)
                SpeedType.FLYING
            else SpeedType.WALKING

        if (args.isEmpty()) {
            sender.sendMessage("§cUsage: /speed <speed> [type] [player]")
            return false
        }

        if (args.size > 1) {
            type =
                when (args[1].uppercase()) {
                    "WALK", "WALKING" -> SpeedType.WALKING
                    "FLY", "FLIGHT", "FLYING" -> SpeedType.FLYING
                    else -> {
                        sender.sendMessage(Config.prefix + " §cInvalid movement type!")
                        return false
                    }
                }
        }

        if (args.size > 2) {
            if (Bukkit.getPlayer(args[2]) == null) {
                sender.sendMessage(Config.prefix + " §cPlayer not found!")
                return false
            } else {
                target = Bukkit.getPlayer(args[2])
                others = true
            }
        }

        try {
            speed = getRealMoveSpeed(
                args[0].toFloat(),
                target.isFlying
            )
        } catch (e: Exception) {
            sender.sendMessage(Config.prefix + " §c" + args[0] + " is not a number")
            return false
        }

        if (speed > 10f)
            speed = 10f
        else if (speed < 0.0001f)
            speed = 0.0001f

        if (!sender.checkPermission("speed", others))
            return false

        if (type == SpeedType.FLYING) {
            target.flySpeed = speed
            if (others)
                sender.sendMessage(Config.prefix + " §aSet ${target.name}('s) flying speed to ${args[0]}")
            target.sendMessage(Config.prefix + " §aYour flying speed has been set to ${args[0]}")
        } else {
            target.walkSpeed = speed
            if (others)
                sender.sendMessage(Config.prefix + " §aSet ${target.name}('s) walking speed to ${args[0]}")
            target.sendMessage(Config.prefix + " §aYour walking speed has been set to ${args[0]}")
        }

        return false
    }

}

private enum class SpeedType {
    WALKING, FLYING
}

private fun getRealMoveSpeed(userSpeed: Float, isFly: Boolean): Float {
    val defaultSpeed =
        if (isFly) 0.1f
        else 0.2f

    val maxSpeed = 1f

    return if (userSpeed < 1f) {
        defaultSpeed * userSpeed
    } else {
        val ratio = (userSpeed - 1) / 9 * (maxSpeed - defaultSpeed)
        ratio + defaultSpeed
    }
}