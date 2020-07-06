package dev.luckynetwork.id.lyrams.commands

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.objects.IMaterial
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack


class ClearCMD : CommandExecutor {

    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        commandName: String?,
        args: Array<out String>?
    ): Boolean {
        if (sender !is Player)
            return false

        var target = sender
        var others = false
        var offset = 0
        var type = ClearType.ALL_EXCEPT_ARMOR
        var itemStack = ItemStack(Material.AIR)

        if (args!!.isNotEmpty() && Bukkit.getPlayer(args[0]) != null) {

            target = Bukkit.getPlayer(args[0]) as Player

            others = true
            offset = 1

        }

        if (!sender.checkPermission("clear", others))
            return false

        if (args.isNotEmpty() && args[0 + offset].isNotEmpty()) {

            if (args[0 + offset] == "**") {
                type = ClearType.ALL
            } else {
                val damage =
                    if (args[0 + offset].contains(":"))
                        args[0 + offset].split(":")[1].toInt()
                    else 0

                try {
                    // tries to use IMaterial to get itemStack
                    if (!args[0 + offset].contains(":"))
                        itemStack = IMaterial.getMaterial(args[0 + offset])

                    // if IMaterial hasn't supported the itemStack yet
                    if (itemStack.type == Material.AIR)
                        itemStack = ItemStack(Material.valueOf(args[0 + offset]), 64, damage.toShort())

                    type = ClearType.SPECIFIC_ITEM

                } catch (e: Exception) {

                    sender.sendMessage("§e§lLuckyNetwork §a/ §c§l" + args[0 + offset] + " §cmight not be an item!")
                    return false

                }

            }
        }


        when (type) {
            ClearType.ALL -> clearInventory(target)
            ClearType.ALL_EXCEPT_ARMOR -> clearInventoryNoArmor(target)
            ClearType.SPECIFIC_ITEM -> clearSpecificItem(target, itemStack.type)
        }

        target.updateInventory()

        when {
            others -> {
                sender.sendMessage("§e§lLuckyNetwork §a/ §aCleared §l" + target.name + "('s) §ainventory!")
                target.sendMessage("§e§lLuckyNetwork §a/ §aInventory cleared!")
            }
            else -> {
                target.sendMessage("§e§lLuckyNetwork §a/ §aInventory cleared!")
            }
        }

        return false

    }
}

private enum class ClearType {
    ALL, ALL_EXCEPT_ARMOR, SPECIFIC_ITEM
}

private fun clearInventory(player: Player) {
    player.inventory.clear()
    player.inventory.armorContents = null
}

private fun clearInventoryNoArmor(player: Player) {
    player.inventory.clear()
}

private fun clearSpecificItem(player: Player, material: Material) {
    player.inventory.remove(material)
}
