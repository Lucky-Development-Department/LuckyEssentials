package dev.luckynetwork.id.lyrams.commands.features.trolls

import dev.luckynetwork.id.lyrams.extensions.applyMetadata
import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.extensions.removeMetadata
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class NoHitCMD : BetterCommand {

    override fun execute(sender: CommandSender, args: Array<String>) {
        if (!Config.trollEnabled || !sender.checkPermission("troll.nohit"))
            return

        var target: Player
        target =
            if (sender !is Player) {
                // console must specify a player
                if (args.isEmpty())
                    return sender.sendMessage(Config.prefix + " §cInvalid usage!")

                if (Bukkit.getPlayer(args[0]) == null)
                    return sender.sendMessage(Config.prefix + " §cPlayer not found!")

                Bukkit.getPlayer(args[0])

                // if executed by player
            } else
                sender

        var others = false
        if (args.isNotEmpty() && sender is Player) {
            if (Bukkit.getPlayer(args[0]) == null)
                return target.sendMessage(Config.prefix + " §cPlayer not found!")

            target = Bukkit.getPlayer(args[0])
            others = true
        }

        if (!sender.checkPermission("troll.nohit", others))
            return

        val state =
            if (target.hasMetadata("NOHIT")) {
                target.removeMetadata("NOHIT")
                true
            } else {
                target.applyMetadata("NOHIT", true)
                false
            }

        when {
            others ->
                if (state)
                    sender.sendMessage(Config.prefix + " §aEnabled hit-others for §l" + target.name + "!")
                else
                    sender.sendMessage(Config.prefix + " §cDisabled hit-others for §l" + target.name + "!")
            else ->
                if (state)
                    target.sendMessage(Config.prefix + " §aYou can now hit other entities!")
                else
                    target.sendMessage(Config.prefix + " §cYou can no longer hit other entities!")
        }

    }

}