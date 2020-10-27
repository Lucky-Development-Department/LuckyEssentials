package dev.luckynetwork.id.lyrams.commands.features.essentials

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.extensions.colorizeTrueOrFalse
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.objects.Slots
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.command.CommandSender

class SlotsCMD : BetterCommand {

    override fun execute(sender: CommandSender, args: Array<String>) {
        if (!sender.checkPermission("slots"))
            return

        if (args.isEmpty())
            return sendUsage(sender)

        when (args[0].toUpperCase()) {
            "RELOAD" -> {
                Slots.reload()
                sender.sendMessage(Config.prefix + " §aSlots cache reloaded!")
            }
            "SAVE" -> {
                Slots.save()
                sender.sendMessage(Config.prefix + " §aSlots data saved!")
            }
            "TOGGLE" -> {
                val state = Slots.toggle(null)
                sender.sendMessage(Config.prefix + " §aSlots Modifier: §l${state.toString().colorizeTrueOrFalse()}!")
            }
            "ON", "ENABLE", "ENABLED" -> {
                val state = Slots.toggle(true)
                sender.sendMessage(Config.prefix + " §aSlots Modifier: §l$state")
            }
            "OFF", "DISABLE", "DISABLED" -> {
                val state = Slots.toggle(false)
                sender.sendMessage(Config.prefix + " §aSlots Modifier: §c§l$state")
            }
            "SET" -> {
                if (args.size != 2)
                    return

                val amount: Int
                try {
                    amount = args[1].toInt()
                } catch (ignored: Exception) {
                    return sender.sendMessage(Config.prefix + " §c" + args[1] + " is not a number")
                }

                Slots.set(amount)
                sender.sendMessage(Config.prefix + " §aSlot: ${Slots.getSlots()} players!")
            }
            "CHECK", "INFO" -> {
                val state = Slots.enabled
                if (state)
                    sender.sendMessage(Config.prefix + " §aSlots Modifier: §l$state")
                else
                    sender.sendMessage(Config.prefix + " §aSlots Modifier: §c§l$state")

                sender.sendMessage(Config.prefix + " §aSlot: ${Slots.getSlots()} players!")
            }
            else -> sendUsage(sender)
        }

    }

}

private fun sendUsage(sender: CommandSender) {
    sender.sendMessage("§cUsage: /slots set <amount>")
    sender.sendMessage("§cUsage: /slots check/info")
    sender.sendMessage("§cUsage: /slots toggle")
    sender.sendMessage("§cUsage: /slots on")
    sender.sendMessage("§cUsage: /slots off")
    sender.sendMessage("§cUsage: /slots reload")
}