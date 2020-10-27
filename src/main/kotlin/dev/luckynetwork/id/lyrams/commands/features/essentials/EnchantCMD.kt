package dev.luckynetwork.id.lyrams.commands.features.essentials

import dev.luckynetwork.id.lyrams.enums.XEnchantment
import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class EnchantCMD : BetterCommand {

    override fun execute(sender: CommandSender, args: Array<String>) {
        if (!sender.checkPermission("enchant"))
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

        if (args.isNotEmpty() && Bukkit.getPlayer(args[0]) != null && sender is Player) {
            target = Bukkit.getPlayer(args[0]) as Player
            others = true
            offset = 1
        }

        if (!sender.checkPermission("enchant", others))
            return

        target.inventory.itemInHand ?: return

        val itemInHand = target.inventory.itemInHand
        val enchantments: ArrayList<String> = ArrayList()
        val argsAsString = args.joinToString(" ")

        when {
            args[0 + offset].contains(",") -> {
                if (others) {
                    for (s in argsAsString
                        .split(target.name + " ")[1]
                        .split(" ")[0]
                        .split(",")) {
                        enchantments.add(s)
                    }
                } else {
                    for (s in argsAsString
                        .split(" ")[0]
                        .split(",")) {
                        enchantments.add(s)
                    }
                }
            }
            else -> {
                enchantments.add(args[0 + offset])
            }
        }
        for (enchantment in enchantments) {
            val level =
                if (enchantment.contains(":"))
                    enchantment.split(":")[1].toInt()
                else 1

            if (level > 0)
                itemInHand.addUnsafeEnchantment(
                    XEnchantment.getByName(
                        if (enchantment.contains(":"))
                            enchantment.split(":")[0]
                        else enchantment
                    ), level
                )
            else
                itemInHand.removeEnchantment(
                    XEnchantment.getByName(
                        if (enchantment.contains(":"))
                            enchantment.split(":")[0]
                        else enchantment
                    )
                )
        }

        target.updateInventory()

    }

}