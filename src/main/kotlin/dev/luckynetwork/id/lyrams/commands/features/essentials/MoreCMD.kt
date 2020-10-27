package dev.luckynetwork.id.lyrams.commands.features.essentials

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class MoreCMD : BetterCommand {

    override fun execute(sender: CommandSender, args: Array<String>) {
        if (sender !is Player || !sender.checkPermission("more"))
            return

        sender.inventory.itemInHand ?: return

        val itemInHand = sender.inventory.itemInHand
        itemInHand.amount = 64
        sender.updateInventory()

    }
}