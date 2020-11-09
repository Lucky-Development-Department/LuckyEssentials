package dev.luckynetwork.id.lyrams.commands.features.essentials

import dev.luckynetwork.id.lyrams.LuckyEssentials
import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.extensions.colorizeTrueOrFalse
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender

class ChatLockCMD : BetterCommand("chatlock", "cl") {

    override fun execute(
        sender: CommandSender,
        commandLabel: String,
        args: Array<String>
    ): Boolean {
        if (!sender.checkPermission("chatlock"))
            return true

        var silent = false
        var anonymous = false

        if (args.isNotEmpty()) {
            when {
                args[0] == "-s" -> silent = true
                args[0] == "-a" -> anonymous = true
                else -> {
                    sendUsage(sender)
                    return true
                }
            }
        }

        LuckyEssentials.isChatLocked = !LuckyEssentials.isChatLocked
        val state = LuckyEssentials.isChatLocked

        if (!silent) {
            if (!anonymous)
                Bukkit.getOnlinePlayers().forEach {
                    it.sendMessage(" ")
                    if (!state)
                        it.sendMessage(Config.prefix + " §aChat has been unlocked by ${sender.name}!")
                    else
                        it.sendMessage(Config.prefix + " §cChat has been locked by ${sender.name}!")
                }
            else
                Bukkit.getOnlinePlayers().forEach {
                    it.sendMessage(" ")
                    if (!state)
                        it.sendMessage(Config.prefix + " §aChat has been unlocked by a staff member!")
                    else
                        it.sendMessage(Config.prefix + " §cChat has been locked by a staff member!")
                }
        } else {
            sender.sendMessage(Config.prefix + " §aChat lock: ${state.toString().colorizeTrueOrFalse}")
        }

        Bukkit.getOnlinePlayers().forEach {
            if (it.checkPermission("chatlock", silent = true))
                if (state)
                    it.sendMessage(Config.prefix + " §c${sender.name} turned on chatlock")
                else
                    it.sendMessage(Config.prefix + " §c${sender.name} turned off chatlock")

        }

        return true
    }

}

private fun sendUsage(sender: CommandSender) {
    sender.sendMessage("§cUsage: /chatlock")
    sender.sendMessage("§cUsage: /chatlock -s")
    sender.sendMessage("§cUsage: /chatlock -a")
    sender.sendMessage("§cInfo: -s means silent. -a means anonymous")
}