package dev.luckynetwork.id.lyrams.commands.features.essentials

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.extensions.getTargetPlayer
import dev.luckynetwork.id.lyrams.objects.Config
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class ExplodeCMD : CommandExecutor {

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        commandName: String,
        args: Array<out String>
    ): Boolean {
        if (sender !is Player || !sender.checkPermission("explode"))
            return false

        val nullSet: Set<Material>? = null
        val targets = args.getTargetPlayer(sender, 0)

        if (targets.isEmpty())
            return false

        val others = !targets.contains(sender) || targets.size > 1
        val targetBlocks = ArrayList<Block>()
        var damage = true
        var power = 4f

        if (others)
            targets.forEach {
                targetBlocks.add(it.location.block)
            }
        else
            targetBlocks.add(sender.getTargetBlock(nullSet, 120))

        if (!sender.checkPermission("explode", others))
            return false

        val argsAsString = args.joinToString(" ")

        if (argsAsString.toLowerCase().contains("-nodamage"))
            damage = false

        if (argsAsString.toLowerCase().contains("-power="))
            power = argsAsString
                .split("-power=")[1].toFloat()

        targetBlocks.forEach {

            val location = Location(
                it.world, it.x.toDouble(), (it.y + 1).toDouble(),
                it.z.toDouble()
            )

            it.world.createExplosion(location, power, damage)

        }

        if (others) {
            targets.forEach {
                it.sendMessage(Config.prefix + " §aYou have been exploded!")
            }
            sender.sendMessage(Config.prefix + " §aExploded ${targets.size} players!")
        }

        return false

    }

}