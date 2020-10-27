package dev.luckynetwork.id.lyrams.commands.features.essentials

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class MoreCMD : BetterCommand("more") {

    override fun execute(
        sender: CommandSender,
        commandLabel: String,
        args: Array<String>
    ): Boolean {
        if (sender !is Player || !sender.checkPermission("more"))
            return false

        sender.inventory.itemInHand ?: return false

        val itemInHand = sender.inventory.itemInHand
        itemInHand.amount = 64
        sender.updateInventory()
        return false
    }
}