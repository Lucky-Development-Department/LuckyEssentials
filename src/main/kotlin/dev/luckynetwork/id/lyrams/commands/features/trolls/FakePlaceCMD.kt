package dev.luckynetwork.id.lyrams.commands.features.trolls

import dev.luckynetwork.id.lyrams.extensions.applyMetadata
import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.extensions.removeMetadata
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class FakePlaceCMD : BetterCommand("fakeplace") {

    override fun execute(
        sender: CommandSender,
        commandLabel: String,
        args: Array<String>
    ): Boolean {
        if (!sender.checkPermission("troll.fakeplace"))
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

        if (!sender.checkPermission("troll.fakeplace", others))
            return false

        val state =
            if (target.hasMetadata("FAKEPLACE")) {
                target.removeMetadata("FAKEPLACE")
                false
            } else {
                target.applyMetadata("FAKEPLACE", true)
                true
            }

        when {
            others ->
                if (state)
                    sender.sendMessage(Config.prefix + " §aEnabled fake-block-placing for §l" + target.name + "!")
                else
                    sender.sendMessage(Config.prefix + " §cDisabled fake-block-placing for §l" + target.name + "!")
            else ->
                if (state)
                    target.sendMessage(Config.prefix + " §aYour placed blocks will now start disappearing!")
                else
                    target.sendMessage(Config.prefix + " §cYour placed blocks will no longer disappear!")
        }

        return false
    }

}