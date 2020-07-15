package dev.luckynetwork.id.lyrams.commands.features

import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.objects.Whitelist
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class EWhitelistCMD : CommandExecutor {

    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        commandName: String?,
        args: Array<out String>?
    ): Boolean {

        if (!sender!!.checkPermission("ewhitelist"))
            return false

        if (args!!.isEmpty()) {
            sendUsage(sender)
            return false
        }

        when (args[0].toUpperCase()) {
            "RELOAD" -> {
                Whitelist.reload()
                sender.sendMessage("§e§lLuckyEssentials §a/ §aWhitelist cache reloaded!")
            }
            "SAVE" -> {
                Whitelist.save()
                sender.sendMessage("§e§lLuckyEssentials §a/ §aWhitelist saved!")
            }
            "TOGGLE" -> {
                val state = Whitelist.toggle(null)
                if (state)
                    sender.sendMessage("§e§lLuckyEssentials §a/ §aWhitelist: §l$state!")
                else
                    sender.sendMessage("§e§lLuckyEssentials §a/ §aWhitelist: §c§l$state!")
            }
            "ON", "ENABLE", "ENABLED" -> {
                val state = Whitelist.toggle(true)
                sender.sendMessage("§e§lLuckyEssentials §a/ §aWhitelist: §l$state")
            }
            "OFF", "DISABLE", "DISABLED" -> {
                val state = Whitelist.toggle(false)
                sender.sendMessage("§e§lLuckyEssentials §a/ §aWhitelist: §c§l$state")
            }
            "ADD" -> {
                if (args.size != 2)
                    return false

                val targets = ArrayList<String>()
                var bulk = false

                if (args[1].toLowerCase().contains(",")) {
                    for (toBeAdded in args[1].split(",")) {
                        if (toBeAdded != "")
                            targets.add(toBeAdded)
                    }
                    bulk = true
                } else {
                    targets.add(args[1].toLowerCase())
                }

                if (bulk) {
                    for (toBeAdded in targets) {
                        val success = Whitelist.add(toBeAdded, false)

                        if (success)
                            sender.sendMessage("§e§lLuckyEssentials §a/ §aAdded $toBeAdded to whitelist!")
                        else
                            sender.sendMessage("§e§lLuckyEssentials §a/ §c$toBeAdded is already whitelisted!")
                    }
                    Whitelist.save()
                } else {
                    for (toBeAdded in targets) {
                        val success = Whitelist.add(toBeAdded, true)

                        if (success)
                            sender.sendMessage("§e§lLuckyEssentials §a/ §aAdded $toBeAdded to whitelist!")
                        else
                            sender.sendMessage("§e§lLuckyEssentials §a/ §c$toBeAdded is already whitelisted!")
                    }
                }

            }
            "REMOVE" -> {
                if (args.size != 2)
                    return false

                val targets = ArrayList<String>()
                var bulk = false

                if (args[1].toLowerCase().contains(",")) {
                    for (toBeRemoved in args[1].split(",")) {
                        if (toBeRemoved != "")
                            targets.add(toBeRemoved)
                    }
                    bulk = true
                } else {
                    targets.add(args[1].toLowerCase())
                }

                if (bulk) {
                    for (toBeRemoved in targets) {
                        val success = Whitelist.remove(toBeRemoved, false)

                        if (success)
                            sender.sendMessage("§e§lLuckyEssentials §a/ §aRemoved $toBeRemoved from the whitelist!")
                        else
                            sender.sendMessage("§e§lLuckyEssentials §a/ §c$toBeRemoved is not whitelisted!")
                    }
                    Whitelist.save()
                } else {
                    for (toBeRemoved in targets) {
                        val success = Whitelist.remove(toBeRemoved, true)

                        if (success)
                            sender.sendMessage("§e§lLuckyEssentials §a/ §aRemoved $toBeRemoved from the whitelist!")
                        else
                            sender.sendMessage("§e§lLuckyEssentials §a/ §c$toBeRemoved is not whitelisted!")
                    }
                }
            }
            "LIST" -> {
                val list = Whitelist.list()

                val state = Whitelist.enabled
                if (state)
                    sender.sendMessage("§e§lLuckyEssentials §a/ §aWhitelist: §l$state")
                else
                    sender.sendMessage("§e§lLuckyEssentials §a/ §aWhitelist: §c§l$state")
                sender.sendMessage("§e§lLuckyEssentials §a/ §aWhitelisted:")
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

                val target = args[1].toLowerCase()

                val check = Whitelist.isWhitelisted(target)
                if (check)
                    sender.sendMessage("§e§lLuckyEssentials §a/ §a$target is whitelisted!")
                else
                    sender.sendMessage("§e§lLuckyEssentials §a/ §c$target is not whitelisted!")
            }
            "CLEAR" -> {
                Whitelist.clear()
                sender.sendMessage("§e§lLuckyEssentials §a/ §aWhitelist cleared!")
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
    sender.sendMessage("§cUsage: /ewl list")
    sender.sendMessage("§cUsage: /ewl clear")
    sender.sendMessage("§cUsage: /ewl toggle")
    sender.sendMessage("§cUsage: /ewl on")
    sender.sendMessage("§cUsage: /ewl off")
    sender.sendMessage("§cUsage: /ewl reload")
}