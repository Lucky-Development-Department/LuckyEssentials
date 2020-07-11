package dev.luckynetwork.id.lyrams.commands.features

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.objects.XEnchantment
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class EnchantCMD : CommandExecutor {

    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        commandName: String?,
        args: Array<out String>?
    ): Boolean {

        if (!sender!!.checkPermission("enchant"))
            return false

        var target: Player

        // casts target
        target =
                // if console executes this
            if (sender !is Player) {
                // console must specify a player
                if (args!!.isEmpty()) {
                    sender.sendMessage("§e§lLuckyEssentials §a/ §cInvalid usage!")
                    return false
                }

                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage("§e§lLuckyEssentials §a/ §cPlayer not found!")
                    return false
                }

                Bukkit.getPlayer(args[0])

                // if executed by player
            } else
                sender

        var others = false
        var offset = 0

        if (args!!.isNotEmpty() && Bukkit.getPlayer(args[0]) != null && sender is Player) {

            target = Bukkit.getPlayer(args[0]) as Player

            others = true
            offset = 1

        }

        if (!sender.checkPermission("enchant", others))
            return false

        target.inventory.itemInHand ?: return false

        val itemInHand = target.inventory.itemInHand
        var enchantment = args[0 + offset]
        var enchantLevel = 1

        if (enchantment.contains(":")) {
            enchantLevel = enchantment.split(":")[1].toInt()
            enchantment = enchantment.split(":")[0]
        } else if (args.size == 2 + offset) {
            try {
                enchantLevel = args[1 + offset].toInt()
            } catch (ignored: Exception) {

            }
        }


        itemInHand.addUnsafeEnchantment(
            XEnchantment.getByName(enchantment), enchantLevel
        )


        target.updateInventory()

        return false

    }

}