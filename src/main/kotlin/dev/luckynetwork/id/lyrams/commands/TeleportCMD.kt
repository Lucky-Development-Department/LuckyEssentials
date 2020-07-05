package dev.luckynetwork.id.lyrams.commands

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TeleportCMD : CommandExecutor {

    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        commandName: String?,
        args: Array<out String>?
    ): Boolean {

        if (sender !is Player)
            return false

        if (!sender.checkPermission("teleport"))
            return false

        commandName ?: return false

        if (args!!.isEmpty()) {
            // todo: send usage message
            return false
        }

        when (commandName.toUpperCase()) {
            "teleport", "tp" -> {
                var target: Player = sender
                var toTarget: Player
                var others = false

                if (args.size == 1) {
                    if (Bukkit.getPlayer(args[0]) == null) {
                        sender.sendMessage("§e§lLuckyNetwork §a/ §cPlayer not found!")
                        return false
                    }

                    toTarget = Bukkit.getPlayer(args[0])

                    if (!sender.checkPermission("teleport", others))
                        return false

                    target.teleport(toTarget.location)
                }

                if (args.size == 2) {
                    if (Bukkit.getPlayer(args[0]) == null) {
                        sender.sendMessage("§e§lLuckyNetwork §a/ §c§l" + args[0] + " §cnot found!")
                        return false
                    }

                    target = Bukkit.getPlayer(args[0])

                    if (Bukkit.getPlayer(args[1]) == null) {
                        sender.sendMessage("§e§lLuckyNetwork §a/ §c§l" + args[1] + " §cnot found!")
                        return false
                    }

                    others = true

                    toTarget = Bukkit.getPlayer(args[1])

                    if (!sender.checkPermission("teleport", others))
                        return false

                    target.teleport(toTarget.location)

                }

            }
            "tppos" -> {
                var target: Player = sender

                var world = sender.world
                var x = args[0].toDouble()
                var y = args[1].toDouble()
                var z = args[2].toDouble()
                var yaw = sender.location.yaw
                var pitch = sender.location.pitch

                if (args.size < 3) {
                    // todo: send usage message
                    return false
                }

                var startFrom = 0

                if (Bukkit.getPlayer(args[0]) != null) {
                    startFrom = 1
                    target = Bukkit.getPlayer(args[0])
                }

                when (args.size + startFrom) {
                    6 -> {
                        world = Bukkit.getWorld(args[0 + startFrom])
                        x = args[1 + startFrom].toDouble()
                        y = args[2 + startFrom].toDouble()
                        z = args[3 + startFrom].toDouble()
                        yaw = args[4 + startFrom].toFloat()
                        pitch = args[5 + startFrom].toFloat()
                    }
                    5 -> {
                        yaw = args[3 + startFrom].toFloat()
                        pitch = args[4 + startFrom].toFloat()
                    }
                    4 -> {
                        world = Bukkit.getWorld(args[0 + startFrom])
                    }
                }


                if (world == null) {
                    sender.sendMessage("§e§lLuckyNetwork §a/ §cWorld not found!")
                    return false
                }

                val location = Location(world, x, y, z, yaw, pitch)

                target.teleport(location)

            }
            "tphere", "s" -> {
                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage("§e§lLuckyNetwork §a/ §cPlayer not found!")
                    return false
                }

                val toTarget: Player = Bukkit.getPlayer(args[0])

                if (!sender.checkPermission("teleport.here"))
                    return false

                toTarget.teleport(sender.location)
            }
        }

        return false
    }

}