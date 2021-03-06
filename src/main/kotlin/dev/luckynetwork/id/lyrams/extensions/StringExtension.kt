package dev.luckynetwork.id.lyrams.extensions

import dev.luckynetwork.id.lyrams.objects.Config
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*
import kotlin.collections.ArrayList

/** colorizes [String] to RED if false and to GREEN if true*/
internal val String.colorizeTrueOrFalse: String
    get() = when {
        this.equals("true", true) -> "§a$this"
        this.equals("false", true) -> "§c$this"
        else -> this
    }

/** colorizes [String] */
internal val String.colorize: String
    get() = ChatColor.translateAlternateColorCodes('&', this)

/** checks if [String] is a UUID */
internal val String.isUUID: Boolean
    get() {
        return try {
            UUID.fromString(this)
            true
        } catch (_: Exception) {
            false
        }
    }

/** checks if [String] is a double */
internal fun String.isDouble(): Boolean =
    try {
        this.toDouble()
        true
    } catch (ignored: Exception) {
        false
    }

/** gets target player from args */
internal fun Array<out String>.getTargetPlayer(
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