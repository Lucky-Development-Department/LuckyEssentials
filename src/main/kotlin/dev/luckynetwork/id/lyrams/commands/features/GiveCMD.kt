package dev.luckynetwork.id.lyrams.commands.features

import dev.luckynetwork.id.lyrams.LuckyEssentials
import dev.luckynetwork.id.lyrams.extensions.asString
import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.objects.XEnchantment
import dev.luckynetwork.id.lyrams.objects.XItemStack
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

/**
 * Good-luck reading this code hahaha
 * I can't read even read my own code :'D
 *
 * I'm killing myself
 */
class GiveCMD : CommandExecutor {

    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        commandName: String?,
        args: Array<out String>?
    ): Boolean {
        var target: Player

        if (!sender!!.checkPermission("give"))
            return false

        // get where should the args start from...
        // its complicated :/
        val offset =
            if (commandName.equals("i", true)) 0
            else 1

        // casts target
        target =
                // if console executes this
            if (sender !is Player) {
                // console must specify a player
                if (args!!.isEmpty()) {
                    sendUsage(sender)
                    return false
                }

                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(LuckyEssentials.prefix + " §cPlease specify a player!")
                    return false
                }

                Bukkit.getPlayer(args[0])

                // if executed by player
            } else
                sender

        // a variable to determine if this command is used to give other
        // players items
        var others = false

        // please specify at least the items... duh
        if (args!!.isEmpty()) {
            sendUsage(sender)
            return false
        }

        // what material
        val material =
            if (args[offset].contains(":"))
                args[offset].split(":")[0].toUpperCase()
            else args[offset].toUpperCase()

        // whats the material damage amount
        val damage =
            if (args[offset].contains(":"))
                args[offset].split(":")[1].toInt()
            else 0

        // how much
        var amount = -1

        // any more properties for the item
        var itemName = ""
        var enchantments: List<String>? = null
        val argsAsString = args.asString()

        // does it contain '-name='
        if (argsAsString.toLowerCase().contains("-name=\""))
            itemName = argsAsString
                .split("name=\"")[1]
                .split("\"")[0]
        else if (argsAsString.toLowerCase().contains("-name="))
            itemName = argsAsString
                .split("name=")[1]
                .split(" ")[0]

        // does it contain '-enchant='
        if (argsAsString.toLowerCase().contains("-enchant="))
            enchantments = argsAsString
                .split("-enchant=")[1]
                .split(" ")[0]
                .split(",")
        else if (argsAsString.toLowerCase().contains("-enchants="))
            enchantments = argsAsString
                .split("-enchants=")[1]
                .split(" ")[0]
                .split(",")


        // is sender is giving items to other player
        if (args.size > 1) {
            if (Bukkit.getPlayer(args[0]) != null && sender is Player) {
                target = Bukkit.getPlayer(args[0]) as Player

                others = true

                // did sender specify the item amount?
                if (args.size > 2) {
                    try {
                        amount = args[2].toInt()
                    } catch (ignored: Exception) {
                        sender.sendMessage(LuckyEssentials.prefix + " §c" + args[2] + " is not a number")
                        return false
                    }

                }

                // surely args[0 + offset] is for the item amount, right?
            } else {

                if (!args[offset + 1].contains("-")) {

                    try {
                        amount = args[offset + 1].toInt()
                    } catch (ignored: Exception) {
                        sender.sendMessage(LuckyEssentials.prefix + " §cPlayer not found!")
                        return false
                    }

                }

            }

        }

        // now the actual permission checking
        if (!sender.checkPermission("give", others))
            return false

        var itemStack: ItemStack?

        try {
            // tries to use XItemStack to get itemStack
            itemStack = XItemStack.getByName(material, amount, damage)

            // if XItemStack hasn't supported the itemStack yet
            if (itemStack == null)
                itemStack = ItemStack(Material.valueOf(material), amount, damage.toShort())

        } catch (e: Exception) {

            sender.sendMessage(LuckyEssentials.prefix + " §c§l$material §cmight not be an item!")
            return false

        }


        if (itemName.isNotEmpty()) {
            val itemMeta: ItemMeta = itemStack.itemMeta
            itemMeta.displayName = ChatColor.translateAlternateColorCodes('&', itemName)
            itemStack.itemMeta = itemMeta
        }

        if (enchantments != null) {
            for (enchantment in enchantments) {
                val enchantLevel =
                    if (enchantment.contains(":"))
                        enchantment.split(":")[1].toInt()
                    else 1

                itemStack.addUnsafeEnchantment(
                    XEnchantment.getByName(
                        if (enchantment.contains(":"))
                            enchantment.split(":")[0]
                        else enchantment
                    ), enchantLevel

                )

            }

        }


        val leftOvers = addOversizedItems(
            target.inventory,
            itemStack
        )


        for (item in leftOvers.values) {
            val world = target.world
            world.dropItemNaturally(target.location, item)
        }

        target.updateInventory()

        when {
            others -> {
                sender.sendMessage(
                    LuckyEssentials.prefix + " §aGiven §l" + target.name + " §a${itemStack.amount}x ${(itemStack.type).toString()
                        .toLowerCase()}!"
                )
                target.sendMessage(
                    LuckyEssentials.prefix + " §aGave you ${itemStack.amount}x ${(itemStack.type).toString().toLowerCase()}!"
                )
            }
            else -> {
                target.sendMessage(
                    LuckyEssentials.prefix + " §aGave you ${itemStack.amount}x ${(itemStack.type).toString().toLowerCase()}!"
                )
            }
        }

        return false

    }

}

private fun sendUsage(sender: CommandSender) {
    sender.sendMessage("§cUsage: /give [player[:damage]/item] [amount]")
    sender.sendMessage("§cUsage: /i [item] [amount]")
}

// Returns what it couldn't store
// Set oversizedStack to below normal stack size to disable oversized stacks
private fun addOversizedItems(
    inventory: Inventory,
    vararg items: ItemStack?
): Map<Int, ItemStack> {
    val leftover: MutableMap<Int, ItemStack> = HashMap()

    // combine items
    val combined =
        arrayOfNulls<ItemStack>(items.size)

    for (item in items) {
        if (item == null || item.amount < 1) {
            continue
        }
        for (j in combined.indices) {
            if (combined[j] == null) {
                combined[j] = item.clone()
                break
            }
            if (combined[j]!!.isSimilar(item)) {
                combined[j]!!.amount = combined[j]!!.amount + item.amount
                break
            }
        }
    }
    for (i in combined.indices) {
        val item = combined[i]
        if (item == null || item.type == Material.AIR) {
            continue
        }

        while (true) {
            // Do we already have a stack of it?
            val maxAmount = 64.coerceAtLeast(item.type.maxStackSize)
            val firstPartial: Int =
                firstPartial(inventory, item, maxAmount)

            // Drat! no partial stack
            if (firstPartial == -1) {
                // Find a free spot!
                val firstFree = inventory.firstEmpty()
                if (firstFree == -1) {
                    // No space at all!
                    leftover[i] = item
                    break
                } else {
                    // More than a single stack!
                    if (item.amount > maxAmount) {
                        val stack = item.clone()
                        stack.amount = maxAmount
                        inventory.setItem(firstFree, stack)
                        item.amount = item.amount - maxAmount
                    } else {
                        // Just store it
                        inventory.setItem(firstFree, item)
                        break
                    }
                }
            } else {
                // So, apparently it might only partially fit, well lets do just that
                val partialItem = inventory.getItem(firstPartial)
                val amount = item.amount
                val partialAmount = partialItem.amount

                // Check if it fully fits
                if (amount + partialAmount <= maxAmount) {
                    partialItem.amount = amount + partialAmount
                    break
                }

                // It fits partially
                partialItem.amount = maxAmount
                item.amount = amount + partialAmount - maxAmount
            }
        }
    }
    return leftover
}

private fun firstPartial(inventory: Inventory, item: ItemStack?, maxAmount: Int): Int {
    if (item == null) {
        return -1
    }
    val stacks = inventory.contents
    for (i in stacks.indices) {
        val cItem = stacks[i]
        if (cItem != null && cItem.amount < maxAmount && cItem.isSimilar(item)) {
            return i
        }
    }
    return -1

}