package dev.luckynetwork.id.lyrams.commands.features.essentials

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.extensions.getNearbyPlayers
import dev.luckynetwork.id.lyrams.extensions.isDouble
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TeleportAllCMD : BetterCommand("teleportall", "tpall", "tpallpos") {

    override fun execute(
        sender: CommandSender,
        commandName: String,
        args: Array<String>
    ): Boolean {
        if (sender !is Player || !sender.checkPermission("teleportall"))
            return false

        when (commandName.uppercase()) {
            "TPALL" -> {
                val targets: List<Player>
                val toTarget: Player

                if (args.isEmpty()) {
                    targets = Bukkit.getOnlinePlayers().filter { it != sender }

                    targets.forEach {
                        it.teleport(sender.location)
                        it.sendMessage(Config.prefix + " §aTeleported you to ${sender.name}")
                    }

                    sender.sendMessage(Config.prefix + " §aTeleported ${targets.size} players to to you")
                } else if (args[0].contains("r=")) {
                    val range = args[0].split("r=")[1]
                    if (!range.isDouble()) {
                        sendUsage(sender)
                        return false
                    }

                    if (args.size == 2) {
                        if (Bukkit.getPlayer(args[1]) == null) {
                            sender.sendMessage(Config.prefix + " §cPlayer not found!")
                            return false
                        }
                        toTarget = Bukkit.getPlayer(args[1])

                        if (!sender.checkPermission("teleportall", true))
                            return false

                        targets = sender.getNearbyPlayers(range.toDouble(), true).filter { it != toTarget }
                        targets.forEach {
                            it.teleport(toTarget.location)
                            it.sendMessage(Config.prefix + " §a${sender.name} teleported you to ${toTarget.name}")
                        }

                        sender.sendMessage(Config.prefix + " §aTeleported ${targets.size} to ${toTarget.name}")
                    } else {
                        targets = sender.getNearbyPlayers(range.toDouble())
                        targets.forEach {
                            it.teleport(sender.location)
                            it.sendMessage(Config.prefix + " §aTeleported you to ${sender.name}")
                        }

                        sender.sendMessage(Config.prefix + " §aTeleported ${targets.size} players to to you")
                    }
                } else {
                    if (Bukkit.getPlayer(args[0]) == null) {
                        sender.sendMessage(Config.prefix + " §cPlayer not found!")
                        return false
                    }
                    toTarget = Bukkit.getPlayer(args[0])

                    if (!sender.checkPermission("teleportall", true))
                        return false

                    targets = Bukkit.getOnlinePlayers().filter { it != toTarget }
                    targets.forEach {
                        it.teleport(toTarget.location)
                        it.sendMessage(Config.prefix + " §a${sender.name} teleported you to ${toTarget.name}")
                    }

                    sender.sendMessage(Config.prefix + " §aTeleported ${targets.size} players to ${toTarget.name}")
                }
            }
            "TPALLPOS" -> {
                var targets = Bukkit.getOnlinePlayers()

                if (args.size < 3) {
                    sendUsage(sender)
                    return false
                }

                val world: World
                var x: Double
                val y: Double
                var z: Double
                val yaw: Float
                val pitch: Float
                var offset = 0

                if (args[0].contains("r=")) {
                    val range = args[0].split("r=")[1]
                    if (!range.isDouble()) {
                        sendUsage(sender)
                        return false
                    }
                    targets = sender.getNearbyPlayers(range.toDouble())
                    offset = 1
                }

                when (args.size - offset) {
                    6 -> {
                        world = Bukkit.getWorld(args[0 + offset])
                        x = args[1 + offset].toDouble()
                        y = args[2 + offset].toDouble()
                        z = args[3 + offset].toDouble()
                        yaw = args[4 + offset].toFloat()
                        pitch = args[5 + offset].toFloat()
                    }
                    5 -> {
                        world = sender.world
                        x = args[0 + offset].toDouble()
                        y = args[1 + offset].toDouble()
                        z = args[2 + offset].toDouble()
                        yaw = args[3 + offset].toFloat()
                        pitch = args[4 + offset].toFloat()
                    }
                    4 -> {
                        world = Bukkit.getWorld(args[0 + offset])
                        x = args[1 + offset].toDouble()
                        y = args[2 + offset].toDouble()
                        z = args[3 + offset].toDouble()
                        yaw = sender.location.yaw
                        pitch = sender.location.pitch
                    }
                    3 -> {
                        world = sender.world
                        x = args[0 + offset].toDouble()
                        y = args[1 + offset].toDouble()
                        z = args[2 + offset].toDouble()
                        yaw = sender.location.yaw
                        pitch = sender.location.pitch
                    }
                    else -> {
                        sendUsage(sender)
                        return false
                    }
                }

                if (x > 0) x += 0.5
                else if (x < 0) x -= 0.5

                if (z > 0) z += 0.5
                else if (z < 0) z -= 0.5

                if (!sender.checkPermission("teleportall.position"))
                    return false

                if (world == null) {
                    sender.sendMessage(Config.prefix + " §cWorld not found!")
                    return false
                }

                val location = Location(world, x, y, z, yaw, pitch)
                targets.forEach {
                    it.teleport(location)
                    it.sendMessage(Config.prefix + " §a${sender.name} teleported you to ${world.name} $x $y $z")
                }

                sender.sendMessage(Config.prefix + " §aTeleported ${targets.size} players to ${world.name} $x $y $z")
            }
            else -> sendUsage(sender)
        }

        return false
    }

}

private fun sendUsage(sender: CommandSender) {
    sender.sendMessage("§cUsage: /tpall [r=<range>/player] [otherPlayer]")
    sender.sendMessage("§cUsage: /tpallpos [r=<range>] [world] <x> <y> <z> [yaw] [pitch]")
}