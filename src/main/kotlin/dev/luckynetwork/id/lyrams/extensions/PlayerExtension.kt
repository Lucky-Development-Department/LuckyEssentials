package dev.luckynetwork.id.lyrams.extensions

import dev.luckynetwork.id.lyrams.objects.Config
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * checks if [Player] has a specific permission
 *
 * @param permission the required permission node
 * @param others is the player performing the action to other players? If yes, then it will add a '.other' suffix on the permission node
 * @param silent do you want the player to be notified if they don't have the appropriate permission?
 *
 * @return does [Player] has the required permission node?
 */
internal fun Player.checkPermission(permission: String, others: Boolean = false, silent: Boolean = false): Boolean {
    val toCheck =
        if (others) "luckyessentials." + permission.lowercase() + ".others"
        else "luckyessentials." + permission.lowercase()

    val allowed = this.hasPermission(toCheck)
    if (!allowed && !silent)
        this.sendMessage(Config.prefix + " §cYou are lacking the permission $toCheck!")

    return allowed
}

/**
 * checks if [CommandSender] has a specific permission
 *
 * @param permission the required permission node
 * @param others is the commandSender performing the action to other players? If yes, then it will add a '.other' suffix on the permission node
 *
 * @return does [CommandSender] has the required permission node?
 */
internal fun CommandSender.checkPermission(permission: String, others: Boolean = false): Boolean {
    val toCheck =
        if (others) "luckyessentials." + permission.lowercase() + ".others"
        else "luckyessentials." + permission.lowercase()

    val allowed = this.hasPermission(toCheck)
    if (!allowed)
        this.sendMessage(Config.prefix + " §cYou are lacking the permission $toCheck!")

    return allowed
}

/**
 * gets all player
 *
 * @param range the range to search, if the range is smaller than 0 then it'll add all online players
 * @param includeSender do you want the sender to be included in the result?
 *
 * @return all nearby players in a specific range
 */
internal fun Player.getNearbyPlayers(
    range: Double = -1.0,
    includeSender: Boolean = false
): ArrayList<Player> {
    val targets = ArrayList<Player>()
    if (range < 0) {
        Bukkit.getOnlinePlayers().forEach { targets.add(it) }
    } else {
        this.getNearbyEntities(range, range, range)
            .filterIsInstance<Player>()
            .forEach {
                targets.add(it)
            }
        if (includeSender)
            targets.add(this)
    }

    return targets
}
