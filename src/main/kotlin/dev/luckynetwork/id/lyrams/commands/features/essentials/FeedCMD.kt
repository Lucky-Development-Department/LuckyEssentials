package dev.luckynetwork.id.lyrams.commands.features.essentials

import com.google.common.base.Joiner
import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.extensions.getTargetPlayer
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.command.CommandSender

class FeedCMD : BetterCommand("feed", "eat") {

    override fun execute(
        sender: CommandSender,
        commandLabel: String,
        args: Array<String>
    ): Boolean {
        if (!sender.checkPermission("feed"))
            return false

        val targets = args.getTargetPlayer(sender, 0)
        val targetNames = ArrayList<String>()
        if (targets.isEmpty())
            return false

        val others = !targets.contains(sender) || targets.size > 1
        if (!sender.checkPermission("feed", others))
            return false

        targets.forEach {
            it.foodLevel = 20
            it.saturation = 20f
            it.sendMessage(Config.prefix + " §aYou have been fed!")
            targetNames.add(it.name)
        }

        if (others) {
            if (targets.size < 21)
                sender.sendMessage(Config.prefix + " §a§l" + Joiner.on(", ").join(targetNames) + " §ahave been fed!")
            else
                sender.sendMessage(Config.prefix + " §a§l" + targets.size + " players §ahave been fed!")
        }

        return false
    }

}