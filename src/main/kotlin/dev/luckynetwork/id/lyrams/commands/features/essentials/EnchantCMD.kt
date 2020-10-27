package dev.luckynetwork.id.lyrams.commands.features.essentials

import dev.luckynetwork.id.lyrams.enums.XEnchantment
import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class EnchantCMD : BetterCommand("enchant", "ench") {

    override fun execute(
        sender: CommandSender,
        commandLabel: String,
        args: Array<String>
    ): Boolean {
        if (!sender.checkPermission("enchant"))
            return false

        var target: Player
        target =
            if (sender !is Player) {
                if (args.isEmpty()) {
                    sender.sendMessage(Config.prefix + " §cInvalid usage!")
                    return false
                }
                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(Config.prefix + " §cPlayer not found!")
                    return false
                }
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
            return false

        target.inventory.itemInHand ?: return false

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
        return false
    }

}