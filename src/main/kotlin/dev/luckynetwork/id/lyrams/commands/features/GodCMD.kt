package dev.luckynetwork.id.lyrams.commands.features

import dev.luckynetwork.id.lyrams.LuckyEssentials
import dev.luckynetwork.id.lyrams.extensions.applyMetadata
import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.extensions.removeMetadata
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GodCMD : CommandExecutor {

    override fun onCommand(sender: CommandSender?, command: Command?, cmd: String?, args: Array<out String>?): Boolean {

        if (!sender!!.checkPermission("god"))
            return false

        var target: Player

        target =
                // if console executes this
            if (sender !is Player) {
                // console must specify a player
                if (args!!.isEmpty()) {
                    sender.sendMessage(LuckyEssentials.prefix + " §cInvalid usage!")
                    return false
                }

                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(LuckyEssentials.prefix + " §cPlayer not found!")
                    return false
                }

                Bukkit.getPlayer(args[0])

                // if executed by player
            } else
                sender

        var others = false

        if (args!!.size == 1) {
            if (Bukkit.getPlayer(args[0]) == null) {
                target.sendMessage(LuckyEssentials.prefix + " §cPlayer not found!")
                return false
            }

            target = Bukkit.getPlayer(args[0])
            others = true

        }

        if (!sender.checkPermission("god", others))
            return false

        val state =
            if (target.hasMetadata("GOD")) {
                target.removeMetadata("GOD")
                false
            } else {
                target.applyMetadata("GOD", true)
                true
            }


        when {
            others ->
                if (state) {
                    sender.sendMessage(LuckyEssentials.prefix + " §aEnabled god mode for §l" + target.name + "!")
                    target.sendMessage(LuckyEssentials.prefix + " §aGod mode enabled!")
                } else {
                    sender.sendMessage(LuckyEssentials.prefix + " §cDisabled god mode for §l" + target.name + "!")
                    target.sendMessage(LuckyEssentials.prefix + " §cGod mode disabled!")
                }
            else ->
                if (state)
                    target.sendMessage(LuckyEssentials.prefix + " §aGod mode enabled!")
                else
                    target.sendMessage(LuckyEssentials.prefix + " §cGod mode disabled!")
        }

        return false

    }

}