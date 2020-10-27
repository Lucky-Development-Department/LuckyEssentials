package dev.luckynetwork.id.lyrams.commands.features.essentials

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.extensions.getTargetPlayer
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SmiteCMD : BetterCommand {

    override fun execute(sender: CommandSender, args: Array<String>) {
        if (sender !is Player || !sender.checkPermission("smite"))
            return

        val nullSet: Set<Material>? = null
        val targets = args.getTargetPlayer(sender, 0)

        if (targets.isEmpty())
            return

        val others = !targets.contains(sender) || targets.size > 1
        val targetBlocks = ArrayList<Block>()

        if (others) targets.forEach { targetBlocks.add(it.location.block) }
        else targetBlocks.add(sender.getTargetBlock(nullSet, 120))

        if (!sender.checkPermission("smite", others))
            return

        targetBlocks.forEach {
            val location = Location(
                it.world, it.x.toDouble(), (it.y + 1).toDouble(),
                it.z.toDouble()
            )
            it.world.strikeLightning(location)
        }

        if (others) {
            targets.forEach { it.sendMessage(Config.prefix + " §aYou have been smitten!") }
            sender.sendMessage(Config.prefix + " §aSmitten ${targets.size} players!")
        }

    }

}