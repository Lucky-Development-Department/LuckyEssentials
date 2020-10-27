package dev.luckynetwork.id.lyrams.commands.features.trolls

import dev.luckynetwork.id.lyrams.extensions.applyMetadata
import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.extensions.removeMetadata
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class OneTapCMD : BetterCommand("onetap") {

    override fun execute(
        sender: CommandSender,
        commandLabel: String,
        args: Array<String>
    ): Boolean {
        if (!sender.checkPermission("troll.onetap"))
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

        if (!sender.checkPermission("troll.onetap", others))
            return false

        val state =
            if (target.hasMetadata("ONETAP")) {
                target.removeMetadata("ONETAP")
                false
            } else {
                target.applyMetadata("ONETAP", true)
                true
            }


        when {
            others ->
                if (state)
                    sender.sendMessage(Config.prefix + " §aEnabled one-tap for §l" + target.name + "!")
                else
                    sender.sendMessage(Config.prefix + " §cDisabled one-tap for §l" + target.name + "!")
            else ->
                if (state)
                    target.sendMessage(Config.prefix + " §aYou are now one tap!")
                else
                    target.sendMessage(Config.prefix + " §cYou are no longer one tap!")
        }

        return false
    }

}