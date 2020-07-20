package dev.luckynetwork.id.lyrams.commands.features

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.objects.XItemStack
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

        if (!sender!!.checkPermission("clear"))
            return false

        var target: Player

        // casts target
        target =
                // if console executes this
            if (sender !is Player) {
                // console must specify a player
                if (args!!.isEmpty()) {
                    sender.sendMessage(Config.prefix + " §cInvalid usage!")
                    return false
                }

                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(Config.prefix + " §cPlayer not found!")
                    return false
                }

                Bukkit.getPlayer(args[0])

                // if executed by player
            } else
                sender

        var others = false
        var offset = 0
        var type = ClearType.ALL_EXCEPT_ARMOR
        var itemStack: ItemStack? = null

        if (args!!.isNotEmpty() && Bukkit.getPlayer(args[0]) != null && sender is Player) {

            target = Bukkit.getPlayer(args[0]) as Player

            others = true
            offset = 1

        }

        if (!sender.checkPermission("clear", others))
            return false

        if (args.size == 1 + offset && args[0 + offset].isNotEmpty()) {

            if (args[0 + offset] == "**") {
                type = ClearType.ALL
            } else {
                val damage =
                    if (args[0 + offset].contains(":"))
                        args[0 + offset].split(":")[1].toInt()
                    else 0

                try {
                    // tries to use IMaterial to get itemStack
                    itemStack = XItemStack.getByName(args[0 + offset], damage)

                    // if IMaterial hasn't supported the itemStack yet
                    if (itemStack == null)
                        itemStack = ItemStack(Material.valueOf(args[0 + offset]), 64, damage.toShort())

                    type = ClearType.SPECIFIC_ITEM

                } catch (e: Exception) {

                    sender.sendMessage(Config.prefix + " §c§l" + args[0 + offset] + " §cmight not be an item!")
                    return false

                }

            }

        }

        if (itemStack == null)
            itemStack = ItemStack(Material.AIR)

        when (type) {
            ClearType.ALL -> clearInventory(
                target
            )
            ClearType.ALL_EXCEPT_ARMOR -> clearInventoryNoArmor(
                target
            )
            ClearType.SPECIFIC_ITEM -> clearSpecificItem(
                target,
                itemStack.type
            )
        }

        target.updateInventory()

        when {
            others -> {
                sender.sendMessage(Config.prefix + " §aCleared §l" + target.name + "('s) §ainventory!")
                target.sendMessage(Config.prefix + " §aInventory cleared!")
            }
            else -> {
                target.sendMessage(Config.prefix + " §aInventory cleared!")
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
