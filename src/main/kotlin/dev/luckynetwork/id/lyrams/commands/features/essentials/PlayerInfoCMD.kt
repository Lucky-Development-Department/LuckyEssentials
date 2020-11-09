package dev.luckynetwork.id.lyrams.commands.features.essentials

import com.google.common.base.Joiner
import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.extensions.colorizeTrueOrFalse
import dev.luckynetwork.id.lyrams.extensions.isUUID
import dev.luckynetwork.id.lyrams.extensions.toDate
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*
import kotlin.collections.ArrayList

class PlayerInfoCommand : BetterCommand("playerinfo", "pinfo") {

    @Suppress("DEPRECATION")
    override fun execute(
        sender: CommandSender,
        commandLabel: String,
        args: Array<String>
    ): Boolean {
        if (!sender.checkPermission("playerinfo"))
            return false

        val target0 =
            if (sender !is Player) {
                if (args.isEmpty()) {
                    sender.sendMessage(Config.prefix + " §cInvalid usage!")
                    return false
                }
                if (args[0].contains("-") || args[0].length > 16) {
                    // uses UUID
                    if (args[0].isUUID) {
                        Bukkit.getOfflinePlayer(UUID.fromString(args[0]))
                    } else {
                        sender.sendMessage(Config.prefix + " §cUUID/Player not found! (1)")
                        return false
                    }
                } else {
                    if (args.isEmpty()) {
                        sender.sendMessage(Config.prefix + " §cInvalid usage!")
                        return false
                    }
                    // uses player name
                    Bukkit.getOfflinePlayer(args[0])
                }
            } else {
                if (args.isEmpty()) {
                    Bukkit.getOfflinePlayer(sender.uniqueId)
                } else {
                    if (args[0].contains("-") || args[0].length > 16) {
                        //uses UUID
                        if (args[0].isUUID) {
                            Bukkit.getOfflinePlayer(UUID.fromString(args[0]))
                        } else {
                            sender.sendMessage(Config.prefix + " §cUUID/Player not found! (2)")
                            return false
                        }
                    } else {
                        // uses player name
                        Bukkit.getOfflinePlayer(args[0])
                    }
                }
            }

        if (Bukkit.getPlayer(target0.uniqueId) != null) {
            getPlayerInfo(sender, Bukkit.getPlayer(target0.uniqueId))
        } else {
            getPlayerInfo(sender, target0)
        }

        return false
    }
}

/**
 * gets info of an online player
 */
private fun getPlayerInfo(sender: CommandSender, player: Player) {
    sender.sendMessage(" ")
    sender.sendMessage("§6Player info: ${player.name}")
    sender.sendMessage(" ")
    
    if (sender is Player) {
        val locationTextComponent = TextComponent("§6Location Info §7(Hover me!)")
        val playerInfoTextComponent = TextComponent("§6Player Info §7(Hover me!)")
        val teleportTextComponent = TextComponent("§aClick me to teleport!")

        val loc = player.location
        locationTextComponent.hoverEvent = HoverEvent(
            HoverEvent.Action.SHOW_TEXT,
            ComponentBuilder(
                """
                        §7${player.name}('s) location:
                        
                        §7X: §a${(loc.x)} 
                        §7Y: §a${(loc.y)} 
                        §7Z: §a${(loc.z)} 
                        §7Yaw: §a${(loc.yaw)}
                        §7Pitch: §a${(loc.pitch)}
                        §7World: §a${(loc.world.name)}
                        ${getDistance(sender, player)}
                    """.trimIndent()
            ).create()
        )

        playerInfoTextComponent.hoverEvent = HoverEvent(
            HoverEvent.Action.SHOW_TEXT,
            ComponentBuilder(
                    """
                        §7${player.name}('s) information:
            
                        §7UUID: §a${player.uniqueId}
                        §7Player name: §a${player.name}
                        §7Display name: §a${player.displayName}
                        §7Playerlist name: §a${player.playerListName}
                        §7IP Address: §a${player.spigot().rawAddress}
                        §7First played: §a${player.firstPlayed.toDate()}
                        
                        §7Health: §a${player.health} / ${player.maxHealth}
                        §7Health scale: §a${player.healthScale}
                        §7Food level: §a${player.foodLevel}
                        §7Saturation: §a${player.saturation}
                        §7Exp: §a${player.exp}
                        §7Level: §a${player.expToLevel}
                        §7Fire ticks: §a${player.fireTicks}
                        ${
                            if (player.lastDamageCause != null) {
                                "§7Last damage cause: §a${player.lastDamageCause.cause}"
                                "§7Last damage: §a${player.lastDamageCause.damage}"
                                "§7Last final damage: §a${player.lastDamageCause.finalDamage}"
                                ""
                            } else {
                                ""
                            }
                        }
                        §7Flight speed: §a${player.flySpeed}
                        §7Walk speed: §a${player.walkSpeed}
                        
                        §7Is op: §a${player.isOp.toString().colorizeTrueOrFalse}
                        §7Is flying: §a${player.isFlying.toString().colorizeTrueOrFalse}
                        §7Is online: §a${player.isOnline.toString().colorizeTrueOrFalse}
                        §7Is dead: §a${player.isDead.toString().colorizeTrueOrFalse}
                        §7Is dead: §a${player.isInsideVehicle.toString().colorizeTrueOrFalse}
                        ${getPotionEffects(player)}
                    """.trimIndent()
            ).create()
        )
        
        teleportTextComponent.clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "/teleport ${player.name}")

        sender.spigot().sendMessage(locationTextComponent)
        sender.spigot().sendMessage(playerInfoTextComponent)
        sender.spigot().sendMessage(teleportTextComponent)
    } else {
        val loc = player.location
        sender.sendMessage(
            """
                §7${player.name}('s) location:
            
                §7X: §a${(loc.x)} 
                §7Y: §a${(loc.y)} 
                §7Z: §a${(loc.z)} 
                §7Yaw: §a${(loc.yaw)}
                §7Pitch: §a${(loc.pitch)}
                §7World: §a${(loc.world.name)}
            """.trimIndent()
        )

        sender.sendMessage(
            """
                §7${player.name}('s) information:
            
                §7UUID: §a${player.uniqueId}
                §7Player name: §a${player.name}
                §7Display name: §a${player.displayName}
                §7Playerlist name: §a${player.playerListName}
                §7IP Address: §a${player.spigot().rawAddress}
                §7First played: §a${player.firstPlayed.toDate()}
                §7Last played: §anow
                        
                §7Health: §a${player.health} / ${player.maxHealth}
                §7Health scale: §a${player.healthScale}
                §7Food level: §a${player.foodLevel}
                §7Saturation: §a${player.saturation}
                §7Exp: §a${player.exp}
                §7Level: §a${player.expToLevel}
                §7Fire ticks: §a${player.fireTicks}
                ${
                    if (player.lastDamageCause != null) {
                        "§7Last damage cause: §a${player.lastDamageCause.cause}"
                        "§7Last damage: §a${player.lastDamageCause.damage}"
                        "§7Last final damage: §a${player.lastDamageCause.finalDamage}"
                        ""
                    } else {
                        ""
                    }
                }
                §7Flight speed: §a${player.flySpeed}
                §7Walk speed: §a${player.walkSpeed}
                        
                §7Is op: §a${player.isOp.toString().colorizeTrueOrFalse}
                §7Is flying: §a${player.isFlying.toString().colorizeTrueOrFalse}
                §7Is online: §a${player.isOnline.toString().colorizeTrueOrFalse}
                §7Is dead: §a${player.isDead.toString().colorizeTrueOrFalse}
                §7Is dead: §a${player.isInsideVehicle.toString().colorizeTrueOrFalse}
                ${getPotionEffects(player)}
            """.trimIndent()
        )
    }
    
    sender.sendMessage(" ")
}

/**
 * gets info of an offline player
 */
private fun getPlayerInfo(sender: CommandSender, player: OfflinePlayer) {
    sender.sendMessage(" ")
    sender.sendMessage("§6Player info: ${player.name}")
    sender.sendMessage(" ")

    if (sender is Player) {
        val playerInfoTextComponent = TextComponent("§6Player Info §7(Hover me!)")

        playerInfoTextComponent.hoverEvent = HoverEvent(
            HoverEvent.Action.SHOW_TEXT,
            ComponentBuilder(
                """
                    §7${player.name}('s) information:

                    §7UUID: §a${player.uniqueId}
                    §7Player name: §a${player.name}
                         
                    §7First played: §a${player.firstPlayed.toDate()}
                    §7Last played: §a${player.lastPlayed.toDate()}
                         
                    §7Is banned: §a${player.isBanned.toString().colorizeTrueOrFalse}
                    §7Is op: §a${player.isOp.toString().colorizeTrueOrFalse}
                    §7Is online: §a${player.isOnline.toString().colorizeTrueOrFalse}
                     
                """.trimIndent()
            ).create()
        )

        sender.spigot().sendMessage(playerInfoTextComponent)
    } else {
        sender.sendMessage(
            """
                §7${player.name}('s) information:

                §7UUID: §a${player.uniqueId}
                §7Player name: §a${player.name}
                         
                §7First played: §a${player.firstPlayed.toDate()}
                §7Last played: §a${player.lastPlayed.toDate()}
                         
                §7Is banned: §a${player.isBanned.toString().colorizeTrueOrFalse}
                §7Is op: §a${player.isOp.toString().colorizeTrueOrFalse}
                §7Is online: §a${player.isOnline.toString().colorizeTrueOrFalse}
                     
            """.trimIndent()
        )
    }

    sender.sendMessage(" ")
}

private fun getDistance(sender: Player, player: Player): String {
    return if (sender.location.world == player.location.world) {
        "§7Distance: §a${player.location.distanceSquared(sender.location)}"
    } else {
        "§7Distance: §cnull"
    }
}

private fun getPotionEffects(player: Player): String {
    val list = ArrayList<String>()
    player.activePotionEffects.forEach { list.add("§a${it.type.name}:${it.amplifier}(${it.duration})") }
    val effects =
        if (list.isEmpty()) "§cNone"
        else Joiner.on(", ").join(list)

    return "§7Active effects: $effects"
}