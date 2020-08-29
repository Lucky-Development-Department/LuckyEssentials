package dev.luckynetwork.id.lyrams.extensions

import dev.luckynetwork.id.lyrams.objects.Config
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

fun String.colorizeTrueOrFalse(): String =
    when {
        this.equals("true", true) -> "§a$this"
        this.equals("false", true) -> "§c$this"
        else -> this
    }

fun String.isDouble(): Boolean =
    try {
        this.toDouble()
        true
    } catch (ignored: Exception) {
        false
    }

fun Array<out String>.getTargetPlayer(
    sender: CommandSender,
    int: Int,
    includeSender: Boolean = true
): ArrayList<Player> {

    val targets = ArrayList<Player>()

    when {
        sender !is Player -> {
            // console must specify a player
            if (this.isEmpty() || this.size < int) {
                sender.sendMessage(Config.prefix + " §cInvalid usage!")
                return targets
            }

            if (Bukkit.getPlayer(this[int]) == null) {
                sender.sendMessage(Config.prefix + " §cPlayer not found!")
                return targets
            }

            targets.add(Bukkit.getPlayer(this[int]))

        }
        // if executed by player
        this.isNotEmpty() -> {

            if (this[int] == ("*") || this[int].equals("@a", true)) {

                for (player in Bukkit.getOnlinePlayers()) {
                    if (player == sender && !includeSender)
                        continue
                    targets.add(player)
                }

            } else if ((this[int].contains("*[r=") || this[int].contains("@a[r=") && this[int].endsWith("]"))) {

                val range = this[int]
                    .split("]")[0]
                    .split("=")[1]

                if (!range.isDouble()) {
                    sender.sendMessage(Config.prefix + " §cInvalid usage!")
                    return targets
                }

                sender.getNearbyEntities(range.toDouble(), range.toDouble(), range.toDouble())
                    .filterIsInstance<Player>()
                    .forEach {
                        targets.add(it)
                    }

                if (includeSender)
                    targets.add(sender)

            } else {

                if (Bukkit.getPlayer(this[int]) == null) {
                    sender.sendMessage(Config.prefix + " §cPlayer not found!")
                    return targets
                } else {
                    targets.add(Bukkit.getPlayer(this[int]))
                }

            }

        }

        else -> {
            targets.add(sender)
        }

    }

    return targets

}

fun getAllPlayers(
    player: Player,
    range: Double = -1.0,
    includeSender: Boolean = false
): ArrayList<Player> {

    val targets = ArrayList<Player>()

    if (range < 0) {
        Bukkit.getOnlinePlayers().forEach {
            targets.add(it)
        }
    } else {

        player.getNearbyEntities(range, range, range)
            .filterIsInstance<Player>()
            .forEach {
                targets.add(it)
            }

        if (includeSender)
            targets.add(player)

    }

    return targets

}
