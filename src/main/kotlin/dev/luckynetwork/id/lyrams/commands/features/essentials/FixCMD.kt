package dev.luckynetwork.id.lyrams.commands.features.essentials

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class FixCMD : BetterCommand {

    override fun execute(sender: CommandSender, args: Array<String>) {
        if (!sender.checkPermission("fix"))
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
            } else
                sender

        var others = false
        var offset = 0

        if (args.isNotEmpty() && sender is Player &&
            !(args[0].equals("hand", true) || args[0].equals("all", true))
        ) {
            if (Bukkit.getPlayer(args[0]) == null)
                return sender.sendMessage(Config.prefix + " §cPlayer not found!")

            target = Bukkit.getPlayer(args[0]) as Player
            others = true
            offset = 1
        }

        if (args.isEmpty() || args[0 + offset].equals("hand", true)) {
            if (!sender.checkPermission("fix", others))
                return

            repairHand(target)
        } else if (args[0 + offset].equals("all", true)) {
            if (!sender.checkPermission("fix.all", others))
                return

            repairAll(target)
        } else {
            sendUsage(sender)
        }
    }

}

private fun sendUsage(sender: CommandSender) {
    sender.sendMessage("§cUsage: /fix [player] [all/hand]")
}

private fun repairHand(player: Player) {
    val itemInHand: ItemStack? = player.inventory.itemInHand

    if (itemInHand == null || itemInHand.type.isBlock || itemInHand.durability == 0.toShort() || itemInHand.type.maxDurability < 1)
        return player.sendMessage(Config.prefix + " §cYou can't fix that!")

    if (itemInHand.durability == 0.toShort())
        return player.sendMessage(Config.prefix + " §cThis item doesn't need repairing!")

    itemInHand.durability = 0.toShort()
    player.sendMessage(Config.prefix + " §aItem repaired!")
    player.updateInventory()
}

private fun repairAll(player: Player) {
    var count = 0
    for (item in player.inventory.contents) {

        if (item == null || item.type.isBlock || item.durability == 0.toShort() || item.type.maxDurability < 1)
            continue

        if (item.durability == 0.toShort())
            continue

        item.durability = 0.toShort()
        count++
    }

    for (item in player.inventory.armorContents) {
        if (item == null || item.type.isBlock || item.durability == 0.toShort() || item.type.maxDurability < 1)
            continue

        if (item.durability == 0.toShort())
            continue

        item.durability = 0.toShort()
        count++
    }

    player.sendMessage(Config.prefix + " §a$count items repaired!")
}