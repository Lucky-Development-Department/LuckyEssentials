package dev.luckynetwork.id.lyrams.commands.features.trolls

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.extensions.colorizeTrueOrFalse
import dev.luckynetwork.id.lyrams.extensions.removeMetadata
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TrollCMD : BetterCommand("troll", "trolls", "luckytroll", "luckytrolls", "lt") {

    override fun execute(
        sender: CommandSender,
        commandLabel: String,
        args: Array<String>
    ): Boolean {
        if (!sender.checkPermission("troll"))
            return false

        var others = false
        if (args.isEmpty() && sender is Player) {
            sender.sendMessage("§cUsage: /troll check [player]")
            sender.sendMessage("§cUsage: /troll clear [player]")
            return false
        }

        var target: Player
        when (args[0].toUpperCase()) {
            "CHECK", "INFO" -> {
                target =
                    if (sender !is Player) {
                        if (args.isEmpty()) {
                            sender.sendMessage(Config.prefix + " §cInvalid usage!")
                            return false
                        }
                        if (Bukkit.getPlayer(args[1]) == null) {
                            sender.sendMessage(Config.prefix + " §cPlayer not found!")
                            return false
                        }
                        Bukkit.getPlayer(args[1])

                    } else
                        sender

                if (args.size == 2 && sender is Player) {
                    if (Bukkit.getPlayer(args[1]) == null) {
                        target.sendMessage(Config.prefix + " §cPlayer not found!")
                        return false
                    } else {
                        target = Bukkit.getPlayer(args[1])
                        others = true
                    }
                }

                if (!sender.checkPermission("troll", others))
                    return false

                sender.sendMessage("§6${target.name}('s) active troll attributes:")
                sender.sendMessage("§6FakePlace: ${target.hasMetadata("FAKEPLACE").toString().colorizeTrueOrFalse()}")
                sender.sendMessage("§6NoBreak: ${target.hasMetadata("NOBREAK").toString().colorizeTrueOrFalse()}")
                sender.sendMessage("§6NoDamage: ${target.hasMetadata("NODAMAGE").toString().colorizeTrueOrFalse()}")
                sender.sendMessage("§6NoHit: ${target.hasMetadata("NOHIT").toString().colorizeTrueOrFalse()}")
                sender.sendMessage("§6NoInteract: ${target.hasMetadata("NOINTERACT").toString().colorizeTrueOrFalse()}")
                sender.sendMessage("§6NoPickup: ${target.hasMetadata("NOPICKUP").toString().colorizeTrueOrFalse()}")
                sender.sendMessage("§6NoPlace: ${target.hasMetadata("NOPLACE").toString().colorizeTrueOrFalse()}")
                sender.sendMessage("§6OneTap: ${target.hasMetadata("ONETAP").toString().colorizeTrueOrFalse()}")

            }

            "CLEAR" -> {
                target =
                    if (sender !is Player) {
                        if (args.isEmpty()) {
                            sender.sendMessage(Config.prefix + " §cInvalid usage!")
                            return false
                        }

                        if (Bukkit.getPlayer(args[1]) == null) {
                            sender.sendMessage(Config.prefix + " §cPlayer not found!")
                            return false
                        }

                        Bukkit.getPlayer(args[1])

                    } else
                        sender

                if (args.size == 2 && sender is Player) {
                    if (Bukkit.getPlayer(args[1]) == null) {
                        target.sendMessage(Config.prefix + " §cPlayer not found!")
                        return false
                    } else {
                        target = Bukkit.getPlayer(args[1])
                        others = true
                    }
                }

                if (!sender.checkPermission("troll", others))
                    return false

                target.removeMetadata("FAKEPLACE")
                target.removeMetadata("NOPICKUP")
                target.removeMetadata("NODAMAGE")
                target.removeMetadata("NOHIT")
                target.removeMetadata("NOPLACE")
                target.removeMetadata("NOBREAK")
                target.removeMetadata("NOINTERACT")
                target.removeMetadata("ONETAP")

                if (others)
                    sender.sendMessage(Config.prefix + " §aCleared Trolls for §l" + target.name + "!")
                else
                    target.sendMessage(Config.prefix + " §aYou are no longer trolled!")
            }
        }

        return false
    }

}