package dev.luckynetwork.id.lyrams.commands.features.trolls

import dev.luckynetwork.id.lyrams.extensions.applyMetadata
import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.extensions.removeMetadata
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class NoDamageCMD : BetterCommand {

    override fun execute(sender: CommandSender, args: Array<String>) {
        if (!Config.trollEnabled || !sender.checkPermission("troll.nodamage"))
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

        if (!sender.checkPermission("troll.nodamage", others))
            return

        val state =
            if (target.hasMetadata("NODAMAGE")) {
                target.removeMetadata("NODAMAGE")
                true
            } else {
                target.applyMetadata("NODAMAGE", true)
                false
            }

        when {

            others ->
                if (state)
                    sender.sendMessage(Config.prefix + " §aEnabled damage-others for §l" + target.name + "!")
                else
                    sender.sendMessage(Config.prefix + " §cDisabled damage-others for §l" + target.name + "!")
            else ->
                if (state)
                    target.sendMessage(Config.prefix + " §aYou can now damage other entities!")
                else
                    target.sendMessage(Config.prefix + " §cYou can no longer damage other entities!")

        }

    }

}