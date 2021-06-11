package dev.luckynetwork.id.lyrams.commands.features.essentials

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.extensions.colorizeTrueOrFalse
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.objects.Whitelist
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.command.CommandSender

class EWhitelistCMD : BetterCommand("ewhitelist", "whitelist", "wl", "ewl") {

    override fun execute(
        sender: CommandSender,
        commandLabel: String,
        args: Array<String>
    ): Boolean {
        if (!sender.checkPermission("ewhitelist"))
            return false
        if (args.isEmpty()) {
            sendUsage(sender)
            return false
        }

        when (args[0].uppercase()) {
            "RELOAD" -> {
                Whitelist.reload()
                sender.sendMessage(Config.prefix + " §aWhitelist cache reloaded!")
            }
            "SAVE" -> {
                Whitelist.save()
                sender.sendMessage(Config.prefix + " §aWhitelist saved!")
            }
            "TOGGLE" -> {
                val state = Whitelist.toggle(null)
                sender.sendMessage(Config.prefix + " §aWhitelist: §l${state.toString().colorizeTrueOrFalse}!")
            }
            "ON", "ENABLE", "ENABLED" -> {
                val state = Whitelist.toggle(true)
                sender.sendMessage(Config.prefix + " §aWhitelist: §l$state")
            }
            "OFF", "DISABLE", "DISABLED" -> {
                val state = Whitelist.toggle(false)
                sender.sendMessage(Config.prefix + " §aWhitelist: §c§l$state")
            }
            "ADD" -> {
                if (args.size != 2)
                    return false

                val targets = ArrayList<String>()
                var bulk = false

                if (args[1].lowercase().contains(",")) {
                    for (toBeAdded in args[1].split(",")) {
                        if (toBeAdded != "")
                            targets.add(toBeAdded)
                    }
                    bulk = true
                } else {
                    targets.add(args[1].lowercase())
                }

                if (bulk) {
                    for (toBeAdded in targets) {
                        val success = Whitelist.add(toBeAdded, false)

                        if (success)
                            sender.sendMessage(Config.prefix + " §aAdded $toBeAdded to whitelist!")
                        else
                            sender.sendMessage(Config.prefix + " §c$toBeAdded is already whitelisted!")
                    }
                    Whitelist.save()
                } else {
                    for (toBeAdded in targets) {
                        val success = Whitelist.add(toBeAdded, true)

                        if (success)
                            sender.sendMessage(Config.prefix + " §aAdded $toBeAdded to whitelist!")
                        else
                            sender.sendMessage(Config.prefix + " §c$toBeAdded is already whitelisted!")
                    }
                }

            }
            "REMOVE" -> {
                if (args.size != 2)
                    return false

                val targets = ArrayList<String>()
                var bulk = false

                if (args[1].lowercase().contains(",")) {
                    for (toBeRemoved in args[1].split(",")) {
                        if (toBeRemoved != "")
                            targets.add(toBeRemoved)
                    }
                    bulk = true
                } else {
                    targets.add(args[1].lowercase())
                }

                if (bulk) {
                    for (toBeRemoved in targets) {
                        val success = Whitelist.remove(toBeRemoved, false)

                        if (success)
                            sender.sendMessage(Config.prefix + " §aRemoved $toBeRemoved from the whitelist!")
                        else
                            sender.sendMessage(Config.prefix + " §c$toBeRemoved is not whitelisted!")
                    }
                    Whitelist.save()
                } else {
                    for (toBeRemoved in targets) {
                        val success = Whitelist.remove(toBeRemoved, true)

                        if (success)
                            sender.sendMessage(Config.prefix + " §aRemoved $toBeRemoved from the whitelist!")
                        else
                            sender.sendMessage(Config.prefix + " §c$toBeRemoved is not whitelisted!")
                    }
                }
            }
            "LIST", "INFO" -> {
                val list = Whitelist.list()

                val state = Whitelist.enabled
                if (state)
                    sender.sendMessage(Config.prefix + " §aWhitelist: §l$state")
                else
                    sender.sendMessage(Config.prefix + " §aWhitelist: §c§l$state")
                sender.sendMessage(Config.prefix + " §aWhitelisted:")
                if (list.isEmpty()) {
                    sender.sendMessage("§cnone")
                } else {
                    for (whitelisted in list)
                        sender.sendMessage(whitelisted)
                }
            }
            "CHECK" -> {
                if (args.size != 2)
                    return false

                val target = args[1].lowercase()

                val check = Whitelist.isWhitelisted(target)
                if (check)
                    sender.sendMessage(Config.prefix + " §a$target is whitelisted!")
                else
                    sender.sendMessage(Config.prefix + " §c$target is not whitelisted!")
            }
            "CLEAR" -> {
                Whitelist.clear()
                sender.sendMessage(Config.prefix + " §aWhitelist cleared!")
            }
            else -> sendUsage(sender)
        }

        return false
    }
}

private fun sendUsage(sender: CommandSender) {
    sender.sendMessage("§cUsage: /ewl add <name>")
    sender.sendMessage("§cUsage: /ewl remove <name>")
    sender.sendMessage("§cUsage: /ewl check <name>")
    sender.sendMessage("§cUsage: /ewl list/info")
    sender.sendMessage("§cUsage: /ewl clear")
    sender.sendMessage("§cUsage: /ewl toggle")
    sender.sendMessage("§cUsage: /ewl on")
    sender.sendMessage("§cUsage: /ewl off")
    sender.sendMessage("§cUsage: /ewl reload")
}