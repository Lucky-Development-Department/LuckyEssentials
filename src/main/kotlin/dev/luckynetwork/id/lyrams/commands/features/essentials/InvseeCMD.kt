package dev.luckynetwork.id.lyrams.commands.features.essentials

import dev.luckynetwork.id.lyrams.extensions.applyMetadata
import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class InvseeCMD : BetterCommand("invsee") {

    override fun execute(
        sender: CommandSender,
        commandLabel: String,
        args: Array<String>
    ): Boolean {
        if (sender !is Player || !sender.checkPermission("invsee"))
            return false
        if (args.isEmpty()) {
            sender.sendMessage("§cUsage: /invsee <target> [armorContents = true/false]!")
            return false
        }

        val target =
            if (Bukkit.getPlayer(args[0]) != null) {
                Bukkit.getPlayer(args[0])
            } else {
                sender.sendMessage(Config.prefix + " §cPlayer not found!")
                return false
            }

        val inventory =
            if (args.size > 1)
                Bukkit.getServer().createInventory(target, 9, "Equipment")
            else
                target.inventory

        if (args.size > 1)
            inventory.contents = target.inventory.armorContents

        sender.closeInventory()
        sender.openInventory(inventory)
        sender.applyMetadata("INVSEE", true)

        return false
    }

}