package dev.luckynetwork.id.lyrams.commands.features.essentials

import com.google.common.base.Joiner
import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.extensions.getTargetPlayer
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.command.CommandSender

class HealCMD : BetterCommand {

    override fun execute(sender: CommandSender, args: Array<String>) {
        if (!sender.checkPermission("heal"))
            return

        val targets = args.getTargetPlayer(sender, 0)
        val targetNames = ArrayList<String>()

        if (targets.isEmpty())
            return

        val others = !targets.contains(sender) || targets.size > 1

        if (!sender.checkPermission("heal", others))
            return

        targets.forEach {
            it.health = 20.0
            it.foodLevel = 20
            it.sendMessage(Config.prefix + " §aYou have been healed!")
            targetNames.add(it.name)
        }

        if (others) {
            if (targets.size < 21)
                sender.sendMessage(Config.prefix + " §a§l" + Joiner.on(", ").join(targetNames) + " §ahave been healed!")
            else
                sender.sendMessage(Config.prefix + " §a§l" + targets.size + " players §ahave been healed!")
        }

    }

}