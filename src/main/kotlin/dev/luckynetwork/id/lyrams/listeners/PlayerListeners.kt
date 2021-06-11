package dev.luckynetwork.id.lyrams.listeners

import dev.luckynetwork.id.lyrams.LuckyEssentials
import dev.luckynetwork.id.lyrams.extensions.applyMetadata
import dev.luckynetwork.id.lyrams.extensions.checkPermission
import dev.luckynetwork.id.lyrams.extensions.colorize
import dev.luckynetwork.id.lyrams.extensions.removeMetadata
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.objects.Lockdown
import dev.luckynetwork.id.lyrams.objects.Slots
import dev.luckynetwork.id.lyrams.objects.Whitelist
import org.bukkit.Bukkit
import org.bukkit.entity.HumanEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByBlockEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import org.bukkit.event.player.PlayerLoginEvent

class PlayerListeners : Listener {

    @EventHandler
    fun onChat(event: AsyncPlayerChatEvent) {
        val player = event.player

        if (LuckyEssentials.isChatLocked && !player.checkPermission("chatlock.bypass", silent = true)) {
            event.isCancelled = true
            player.sendMessage(Config.prefix + " §cChat is currently locked!")
        }
    }

    @EventHandler
    fun onInteract(event: PlayerInteractAtEntityEvent) {
        if (Config.disabledCommands.contains("invsee"))
            return

        val player = event.player
        val target = event.rightClicked

        if (target !is Player || !player.hasMetadata("vanished"))
            return

        player.closeInventory()
        player.openInventory(target.inventory)
        player.applyMetadata("INVSEE", true)
    }

    @EventHandler
    fun onPlayerDamage(event: EntityDamageEvent) {
        if (event.entity == null || event.entity !is Player)
            return

        val victim = event.entity as Player
        if (victim.hasMetadata("GOD"))
            event.isCancelled = true
    }

    @EventHandler
    fun onPlayerDamage2(event: EntityDamageByBlockEvent) {
        if (event.damager == null || event.entity == null || event.entity !is Player)
            return

        val victim = event.entity as Player
        if (victim.hasMetadata("GOD"))
            event.isCancelled = true
    }

    @EventHandler
    fun onPlayerDamage3(event: EntityDamageByEntityEvent) {
        if (event.damager == null || event.entity == null || event.entity !is Player)
            return

        val victim = event.entity as Player
        if (victim.hasMetadata("GOD"))
            event.isCancelled = true
    }

    @EventHandler
    fun onConnect(event: PlayerLoginEvent) {
        val player = event.player

        if (Lockdown.enabled) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§cCould not connect to a default or fallback server, please try again later: io.netty.channel.AbstractChannel\$AnnotatedConnectionException")
        } else if (Whitelist.enabled) {
            if (Whitelist.isWhitelisted(player.name.toLowerCase())) return

            event.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, Whitelist.kickMessage.colorize)
        }
    }

    /*
    * Original code from MYSlots
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    fun onConnect2(event: PlayerLoginEvent) {
        if (!Slots.enabled)
            return

        val player = event.player
        when (event.result) {
            PlayerLoginEvent.Result.KICK_FULL, PlayerLoginEvent.Result.ALLOWED -> {
                if (Bukkit.getOnlinePlayers().size < Slots.getSlots()) {
                    event.allow()
                } else if (Bukkit.getOnlinePlayers().size >= Slots.amount && player.checkPermission("join_full", silent = true)) {
                    event.allow()
                } else {
                    event.disallow(PlayerLoginEvent.Result.KICK_FULL, Slots.fullMessage.colorize)
                }
            }
            else -> return
        }
    }

    /*
    * Original code from EssentialsX
     */
    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        if (Config.disabledCommands.contains("invsee"))
            return

        val topInventory = event.view.topInventory
        val inventoryType = topInventory.type
        var refreshPlayer: Player? = null

        when {
            inventoryType == InventoryType.PLAYER -> {
                val whoClicked = event.whoClicked as Player
                val inventoryOwner = topInventory.holder
                if (inventoryOwner !is HumanEntity)
                    return

                inventoryOwner as Player
                if (whoClicked.hasMetadata("INVSEE")) {
                    refreshPlayer = whoClicked

                    if ((!whoClicked.checkPermission("invsee.modify", silent = true) || !inventoryOwner.isOnline))
                        event.isCancelled = true
                    else
                        Bukkit.getScheduler()
                            .scheduleSyncDelayedTask(LuckyEssentials.instance, inventoryOwner::updateInventory, 1)
                }
            }

            inventoryType == InventoryType.CHEST && topInventory.size == 9 -> {
                val whoClicked = event.whoClicked as Player
                val inventoryOwner = topInventory.holder
                if (inventoryOwner !is HumanEntity)
                    return

                if (whoClicked.hasMetadata("INVSEE")) {
                    event.isCancelled = true
                    refreshPlayer = whoClicked
                }
            }
        }

        refreshPlayer ?: return
        Bukkit.getScheduler().scheduleSyncDelayedTask(LuckyEssentials.instance, refreshPlayer::updateInventory, 1)
    }

    /*
    * Original code from EssentialsX
     */
    @EventHandler
    fun onInventoryClose(event: InventoryCloseEvent) {
        if (Config.disabledCommands.contains("invsee"))
            return

        val topInventory = event.view.topInventory
        val inventoryType = topInventory.type
        val player = event.player as Player

        when (inventoryType) {
            InventoryType.PLAYER -> {
                player.removeMetadata("INVSEE")
            }

            InventoryType.CHEST -> {
                val inventoryOwner = topInventory.holder
                if (inventoryOwner !is Player)
                    return

                player.removeMetadata("INVSEE")
            }

            else -> return
        }

        Bukkit.getScheduler().scheduleSyncDelayedTask(LuckyEssentials.instance, player::updateInventory, 1)
    }

}