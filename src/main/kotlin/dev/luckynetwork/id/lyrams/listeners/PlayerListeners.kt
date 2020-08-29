package dev.luckynetwork.id.lyrams.listeners

import dev.luckynetwork.id.lyrams.LuckyEssentials
import dev.luckynetwork.id.lyrams.extensions.applyMetadata
import dev.luckynetwork.id.lyrams.extensions.checkPermissionSilent
import dev.luckynetwork.id.lyrams.extensions.removeMetadata
import dev.luckynetwork.id.lyrams.objects.Slots
import dev.luckynetwork.id.lyrams.objects.Whitelist
import org.bukkit.Bukkit
import org.bukkit.ChatColor
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
import org.bukkit.event.player.PlayerChangedWorldEvent
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import org.bukkit.event.player.PlayerLoginEvent


class PlayerListeners : Listener {

    @EventHandler
    fun onInteract(event: PlayerInteractAtEntityEvent) {
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

        if (!Whitelist.enabled)
            return

        if (Whitelist.isWhitelisted(player.name.toLowerCase()))
            return

        event.disallow(
            PlayerLoginEvent.Result.KICK_WHITELIST,
            ChatColor.translateAlternateColorCodes('&', Whitelist.kickMessage)
        )
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

                if (Bukkit.getOnlinePlayers().size < Slots.getSlots())
                    event.allow()
                else if (Bukkit.getOnlinePlayers().size >= Slots.amount && player.checkPermissionSilent("join_full"))
                    event.allow()
                else
                    event.disallow(
                        PlayerLoginEvent.Result.KICK_FULL,
                        ChatColor.translateAlternateColorCodes('&', Slots.fullMessage)
                    )

            }

            else -> return

        }

    }

    @EventHandler
    fun onWorldChange(event: PlayerChangedWorldEvent) {
        val player = event.player

        if (player.checkPermissionSilent("keepfly"))
            return

        if (player.allowFlight)
            player.allowFlight = false

    }

    /*
    * Original code from EssentialsX
     */
    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
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

                    if ((!whoClicked.checkPermissionSilent("invsee.modify") || !inventoryOwner.isOnline))
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
        val topInventory = event.view.topInventory
        val inventoryType = topInventory.type
        val player = event.player as Player

        when (inventoryType) {
            InventoryType.PLAYER -> {
                if (player.hasMetadata("INVSEE"))
                    player.removeMetadata("INVSEE")

            }

            InventoryType.CHEST -> {
                val inventoryOwner = topInventory.holder

                if (inventoryOwner !is Player)
                    return

                if (player.hasMetadata("INVSEE"))
                    player.removeMetadata("INVSEE")

            }

            else -> return

        }

        Bukkit.getScheduler().scheduleSyncDelayedTask(LuckyEssentials.instance, player::updateInventory, 1)

    }

}