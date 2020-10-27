package dev.luckynetwork.id.lyrams.extensions

import dev.luckynetwork.id.lyrams.objects.Config
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

internal fun Player.checkPermission(permission: String, others: Boolean = false, silent: Boolean = false): Boolean {
    val toCheck =
        if (others) "luckyessentials." + permission.toLowerCase() + ".others"
        else "luckyessentials." + permission.toLowerCase()

    val boolean = this.hasPermission(toCheck)
    if (!boolean && !silent)
        this.sendMessage(Config.prefix + " §cYou are lacking the permission $toCheck!")

    return boolean
}

@Deprecated("lol")
internal fun Player.checkPermissionSilent(permission: String, others: Boolean = false): Boolean {
    val toCheck =
        if (others) "luckyessentials." + permission.toLowerCase() + ".others"
        else "luckyessentials." + permission.toLowerCase()

    return hasPermission(toCheck)
}

internal fun CommandSender.checkPermission(
    permission: String,
    others: Boolean = false,
    silent: Boolean = false
): Boolean {
    val toCheck =
        if (others) "luckyessentials." + permission.toLowerCase() + ".others"
        else "luckyessentials." + permission.toLowerCase()

    val boolean = this.hasPermission(toCheck)
    if (!boolean && !silent)
        this.sendMessage(Config.prefix + " §cYou are lacking the permission $toCheck!")

    return boolean
}