package dev.luckynetwork.id.lyrams.commands.features.essentials

import com.google.common.base.Joiner
import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.extensions.colorizeTrueOrFalse
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*
import kotlin.collections.ArrayList

class PlayerInfoCMD : BetterCommand {

    @Suppress("DEPRECATION")
    override fun execute(sender: CommandSender, args: Array<String>) {
        if (!sender.checkPermission("playerinfo"))
            return

        var target: Any
        target =
            if (sender !is Player) {
                // console must specify a player
                if (args.isEmpty())
                    return sender.sendMessage(Config.prefix + " §cInvalid usage!")

                Bukkit.getOfflinePlayer(args[0])

                // if executed by player
            } else
                sender

        if (args.isNotEmpty() && sender is Player)
            target = Bukkit.getOfflinePlayer(args[0])

        if (sender is Player) {
            val locationTextComponent = TextComponent("§6Location Info §7(Hover me!)")
            val playerInfoTextComponent = TextComponent("§6Player Info §7(Hover me!)")
            val teleportTextComponent = TextComponent("§aClick me to teleport!")

            if (target is Player) {

                val loc = target.location
                locationTextComponent.hoverEvent = HoverEvent(
                    HoverEvent.Action.SHOW_TEXT,
                    ComponentBuilder(
                        """
                        §7${target.name}('s) location:
                        
                        §7X: §a${(loc.x)} 
                        §7Y: §a${(loc.y)} 
                        §7Z: §a${(loc.z)} 
                        §7Yaw: §a${(loc.yaw)}
                        §7Pitch: §a${(loc.pitch)}
                        §7World: §a${(loc.world.name)}
                        ${
                            if (sender.location.world == loc.world) {
                                "§7Distance: §a${loc.distanceSquared(sender.location)}"
                                ""
                            } else {
                                ""
                            }
                        }
                        """.trimIndent()
                    ).create()
                )

                val potionEffectList = ArrayList<String>()
                for (potionEffect in target.activePotionEffects) {
                    potionEffectList.add("${potionEffect.type}:${potionEffect.amplifier}(${potionEffect.duration})")
                }

                playerInfoTextComponent.hoverEvent = HoverEvent(
                    HoverEvent.Action.SHOW_TEXT,
                    ComponentBuilder(
                        """
                        §7${target.name}('s) information:
            
                        §7UUID: §a${target.uniqueId}
                        §7Player name: §a${target.name}
                        §7Display name: §a${target.displayName}
                        §7Playerlist name: §a${target.playerListName}
                        §7IP Address: §a${target.spigot().rawAddress}
                        §7First played: §a${target.firstPlayed.toDate()}
                        §7Last played: §anow
                        
                        §7Health: §a${target.health} / ${target.maxHealth}
                        §7Health scale: §a${target.healthScale}
                        §7Food level: §a${target.foodLevel}
                        §7Saturation: §a${target.saturation}
                        §7Exp: §a${target.exp}
                        §7Level: §a${target.expToLevel}
                        §7Fire ticks: §a${target.fireTicks}
                        ${
                            if (target.lastDamageCause != null) {
                                "§7Last damage cause: §a${target.lastDamageCause.cause}"
                                "§7Last damage: §a${target.lastDamageCause.damage}"
                                "§7Last final damage: §a${target.lastDamageCause.finalDamage}"
                                ""
                            } else {
                                ""
                            }
                        }
                        §7Flight speed: §a${target.flySpeed}
                        §7Walk speed: §a${target.walkSpeed}
                        
                        §7Is op: §a${target.isOp.toString().colorizeTrueOrFalse()}
                        §7Is flying: §a${target.isFlying.toString().colorizeTrueOrFalse()}
                        §7Is online: §a${target.isOnline.toString().colorizeTrueOrFalse()}
                        §7Is dead: §a${target.isDead.toString().colorizeTrueOrFalse()}
                        §7Is dead: §a${target.isInsideVehicle.toString().colorizeTrueOrFalse()}
                        ${
                            if (potionEffectList.isNotEmpty()) {
                                ""
                                "§7Active effects: §a" + Joiner.on(", ").join(potionEffectList)
                                ""
                            } else {
                                ""
                            }
                        }
                        """.trimIndent()
                    ).create()
                )

                teleportTextComponent.clickEvent = ClickEvent(
                    ClickEvent.Action.RUN_COMMAND, "/teleport ${target.name}"
                )

            } else {
                locationTextComponent.hoverEvent = HoverEvent(
                    HoverEvent.Action.SHOW_TEXT, ComponentBuilder("§cPlayer not found!").create()
                )

                playerInfoTextComponent.hoverEvent = HoverEvent(
                    HoverEvent.Action.SHOW_TEXT,
                    ComponentBuilder(
                        """
                         §7${target.name}('s) information:

                         §7UUID: §a${target.uniqueId}
                         §7Player name: §a${target.name}
                         
                         §7First played: §a${target.firstPlayed.toDate()}
                         §7Last played: §a${target.lastPlayed.toDate()}
                         
                         §7Is banned: §a${target.isBanned.toString().colorizeTrueOrFalse()}
                         §7Is op: §a${target.isOp.toString().colorizeTrueOrFalse()}
                         §7Is online: §a${target.isOnline.toString().colorizeTrueOrFalse()}
                     
                        """.trimIndent()
                    ).create()
                )

            }

            sender.sendMessage(" ")
            sender.sendMessage(
                "§6Player info: ${
                    if (target == sender)
                        ""
                    else
                        args[0]
                }"
            )
            sender.sendMessage(" ")
            sender.spigot().sendMessage(locationTextComponent)
            sender.spigot().sendMessage(playerInfoTextComponent)
            if (target is Player)
                sender.spigot().sendMessage(teleportTextComponent)
            sender.sendMessage(" ")

        } else {
            sender.sendMessage(" ")
            sender.sendMessage(
                "§6Player info: ${
                    if (target == sender)
                        ""
                    else
                        args[0]
                }"
            )

            if (target is Player) {
                val loc = target.location
                sender.sendMessage(
                    """
                        §7${target.name}('s) location:
                        
                        §7X: §a${(loc.x)} 
                        §7Y: §a${(loc.y)} 
                        §7Z: §a${(loc.z)} 
                        §7Yaw: §a${(loc.yaw)}
                        §7Pitch: §a${(loc.pitch)}
                        §7World: §a${(loc.world.name)}
                        
                        """.trimIndent()
                )

                val potionEffectList = ArrayList<String>()
                target.activePotionEffects.forEach {
                    potionEffectList.add("${it.type}:${it.amplifier}(${it.duration})")
                }

                sender.sendMessage(
                    """
                        §7${target.name}('s) information:
            
                        §7UUID: §a${target.uniqueId}
                        §7Player name: §a${target.name}
                        §7Display name: §a${target.displayName}
                        §7Playerlist name: §a${target.playerListName}
                        §7IP Address: §a${target.spigot().rawAddress}
                        §7First played: §a${target.firstPlayed.toDate()}
                        §7Last played: §anow
                        
                        §7Health: §a${target.health} / ${target.maxHealth}
                        §7Health scale: §a${target.healthScale}
                        §7Food level: §a${target.foodLevel}
                        §7Saturation: §a${target.saturation}
                        §7Exp: §a${target.exp}
                        §7Level: §a${target.expToLevel}
                        §7Fire ticks: §a${target.fireTicks}
                        ${
                        if (target.lastDamageCause != null) {
                            "§7Last damage cause: §a${target.lastDamageCause.cause}"
                            "§7Last damage: §a${target.lastDamageCause.damage}"
                            "§7Last final damage: §a${target.lastDamageCause.finalDamage}"
                            ""
                        } else {
                            ""
                        }
                    }
                        §7Flight speed: §a${target.flySpeed}
                        §7Walk speed: §a${target.walkSpeed}
                        
                        §7Is op: §a${target.isOp.toString().colorizeTrueOrFalse()}
                        §7Is flying: §a${target.isFlying.toString().colorizeTrueOrFalse()}
                        §7Is online: §a${target.isOnline.toString().colorizeTrueOrFalse()}
                        §7Is dead: §a${target.isDead.toString().colorizeTrueOrFalse()}
                        §7Is dead: §a${target.isInsideVehicle.toString().colorizeTrueOrFalse()}
                        ${
                        if (potionEffectList.isNotEmpty()) {
                            ""
                            "§7Active effects: §a" + Joiner.on(", ").join(potionEffectList)
                            ""
                        } else {
                            ""
                        }
                    }
                        """.trimIndent()
                )

            } else {
                sender.sendMessage(
                    """
                        §7${target.name}('s) information:

                         §7UUID: §a${target.uniqueId}
                         §7Player name: §a${target.name}
                         
                         §7First played: §a${target.firstPlayed.toDate()}
                         §7Last played: §a${target.lastPlayed.toDate()}
                         
                         §7Is banned: §a${target.isBanned.toString().colorizeTrueOrFalse()}
                         §7Is op: §a${target.isOp.toString().colorizeTrueOrFalse()}
                         §7Is online: §a${target.isOnline.toString().colorizeTrueOrFalse()}
                     
                        """.trimIndent()
                )

            }

            sender.sendMessage(" ")
        }

    }

}

fun Long.toDate(): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    calendar.timeZone = TimeZone.getTimeZone("Asia/Jakarta")

    val mYear = calendar[Calendar.YEAR]
    val mMonth = calendar[Calendar.MONTH]
    val mDay = calendar[Calendar.DAY_OF_MONTH]
    val mAmPm =
        if (calendar[Calendar.AM_PM] == 1) "PM"
        else "AM"
    val mHour = calendar[Calendar.HOUR]
    val mMinute = calendar[Calendar.MINUTE]
    val mSecond = calendar[Calendar.SECOND]

    return "$mDay-$mMonth-$mYear $mHour:$mMinute:$mSecond $mAmPm"
}