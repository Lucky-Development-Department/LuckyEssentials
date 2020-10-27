package dev.luckynetwork.id.lyrams.commands.features.essentials

import dev.luckynetwork.id.lyrams.extensions.applyMetadata
import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.extensions.removeMetadata
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GodCMD : BetterCommand("god") {

    override fun execute(
        sender: CommandSender,
        commandLabel: String,
        args: Array<String>
    ): Boolean {
        if (!sender.checkPermission("god"))
            return false

        var target: Player
        target =
            if (sender !is Player) {
                if (args.isEmpty()) {
                    sender.sendMessage(Config.prefix + " §cInvalid usage!")
                    return false
                }
                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(Config.prefix + " §cPlayer not found!")
                    return false
                }
                Bukkit.getPlayer(args[0])

            } else
                sender

        var others = false
        if (args.isNotEmpty() && sender is Player) {
            if (Bukkit.getPlayer(args[0]) == null) {
                target.sendMessage(Config.prefix + " §cPlayer not found!")
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
                    sender.sendMessage(Config.prefix + " §aEnabled god mode for §l" + target.name + "!")
                    target.sendMessage(Config.prefix + " §aGod mode enabled!")
                } else {
                    sender.sendMessage(Config.prefix + " §cDisabled god mode for §l" + target.name + "!")
                    target.sendMessage(Config.prefix + " §cGod mode disabled!")
                }
            else ->
                if (state)
                    target.sendMessage(Config.prefix + " §aGod mode enabled!")
                else
                    target.sendMessage(Config.prefix + " §cGod mode disabled!")
        }

        return false
    }

}