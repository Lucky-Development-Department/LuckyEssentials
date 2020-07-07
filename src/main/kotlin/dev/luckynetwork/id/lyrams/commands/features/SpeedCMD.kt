package dev.luckynetwork.id.lyrams.commands.features

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SpeedCMD : CommandExecutor {


    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        commandName: String?,
        args: Array<out String>?
    ): Boolean {

        if (!sender!!.checkPermission("speed"))
            return false

        if (sender !is Player)
            return false

        // casts target
        var target: Player = sender

        var others = false
        var speed: Float
        var type =
            if (target.isFlying)
                SpeedType.FLYING
            else SpeedType.WALKING

        if (args!!.isEmpty()) {
            sender.sendMessage("§cUsage: /speed <speed> [type] [player]")
            return false
        }

        if (args.size > 1) {

            type =
                when (args[1].toUpperCase()) {
                    "WALK" -> SpeedType.WALKING
                    "FLY", "FLIGHT", "FLYING" -> SpeedType.FLYING
                    else -> {
                        sender.sendMessage("§e§lLuckyEssentials §a/ §cInvalid movement type!")
                        return false
                    }
                }

        }

        if (args.size > 2) {
            if (Bukkit.getPlayer(args[2]) == null) {
                sender.sendMessage("§e§lLuckyEssentials §a/ §cPlayer not found!")
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
            sender.sendMessage("§e§lLuckyEssentials §a/ §c" + args[0] + " is not a number")
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
            sender.sendMessage("§e§lLuckyEssentials §a/ §aYour flying speed has been set to ${args[0]}")
        } else {
            sender.sendMessage("§e§lLuckyEssentials §a/ §aYour walking speed has been set to ${args[0]}")
            target.walkSpeed = speed
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