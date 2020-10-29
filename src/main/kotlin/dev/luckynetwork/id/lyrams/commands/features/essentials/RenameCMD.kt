package dev.luckynetwork.id.lyrams.commands.features.essentials

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.extensions.colorize
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class RenameCMD : BetterCommand("rename") {

    override fun execute(sender: CommandSender, commandLabel: String, args: Array<String>): Boolean {
        if (sender !is Player || sender.checkPermission("rename"))
            return false

        val itemStack = sender.inventory.itemInHand ?: return false
        val itemMeta = itemStack.itemMeta
        val name = args.joinToString(" ").colorize()

        itemMeta.displayName = name
        itemStack.itemMeta = itemMeta
        sender.updateInventory()

        return false
    }

}