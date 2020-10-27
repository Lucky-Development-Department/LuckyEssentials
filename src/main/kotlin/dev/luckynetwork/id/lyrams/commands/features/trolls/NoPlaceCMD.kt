package dev.luckynetwork.id.lyrams.commands.features.trolls

import dev.luckynetwork.id.lyrams.extensions.applyMetadata
import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.extensions.removeMetadata
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class NoPlaceCMD : BetterCommand("noplace") {

    override fun execute(
        sender: CommandSender,
        commandLabel: String,
        args: Array<String>
    ): Boolean {
        if (!sender.checkPermission("troll.noplace"))
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

        if (!sender.checkPermission("troll.noplace", others))
            return false

        val state =
            if (target.hasMetadata("NOPLACE")) {
                target.removeMetadata("NOPLACE")
                true
            } else {
                target.applyMetadata("NOPLACE", true)
                false
            }

        when {
            others ->
                if (state)
                    sender.sendMessage(Config.prefix + " §aEnabled block-placing for §l" + target.name + "!")
                else
                    sender.sendMessage(Config.prefix + " §cDisabled block-placing for §l" + target.name + "!")
            else ->
                if (state)
                    target.sendMessage(Config.prefix + " §aYou can now place blocks!")
                else
                    target.sendMessage(Config.prefix + " §cYou can no longer place blocks!")
        }

        return false
    }

}