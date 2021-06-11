package dev.luckynetwork.id.lyrams.commands.features.essentials

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.extensions.colorizeTrueOrFalse
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.objects.Lockdown
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.command.CommandSender

class LockdownCMD : BetterCommand("lockdown") {

    override fun execute(
        sender: CommandSender,
        commandName: String,
        args: Array<String>
    ): Boolean {
        if (!sender.checkPermission("lockdown"))
            return false

        Lockdown.enabled = !Lockdown.enabled

        sender.sendMessage(Config.prefix + " §aLockdown: ${Lockdown.enabled.toString().colorizeTrueOrFalse}")
        return false
    }
}