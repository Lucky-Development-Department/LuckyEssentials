package dev.luckynetwork.id.lyrams.listeners

import dev.luckynetwork.id.lyrams.LuckyEssentials
import dev.luckynetwork.id.lyrams.extensions.checkPermissionSilent
import dev.luckynetwork.id.lyrams.extensions.removeMetadata
import dev.luckynetwork.id.lyrams.objects.Whitelist
import org.bukkit.Bukkit
import org.bukkit.entity.HumanEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerChangedWorldEvent
import org.bukkit.event.player.PlayerLoginEvent

class PlayerListeners : Listener {

    @EventHandler
    fun onConnect(event: PlayerLoginEvent) {
        val player = event.player

        if (!Whitelist.enabled)
            return

        if (Whitelist.isWhitelisted(player.name.toLowerCase()))
            return

        event.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, Whitelist.whitelistMessage)
    }

    @EventHandler
    fun onWorldChange(event: PlayerChangedWorldEvent) {
        val player = event.player

        if (player.checkPermissionSilent("keepfly"))
            return

        if (player.allowFlight)
            player.allowFlight = false

    }

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        val topInventory = event.view.topInventory
        val inventoryType = topInventory.type
        var refreshPlayer: Player? = null

        when {
            inventoryType == InventoryType.PLAYER -> {
                val whoClicked = event.whoClicked as Player
                val inventoryOwner = topInventory.holder

                if (inventoryOwner !is HumanEntity) {
                    return
                }

                if (whoClicked.hasMetadata("INVSEE") && (!whoClicked.checkPermissionSilent("invsee.modify") || !(inventoryOwner as Player).isOnline)) {
                    event.isCancelled = true
                    refreshPlayer = whoClicked
                }

            }

            inventoryType == InventoryType.CHEST && topInventory.size == 9 -> {
                val whoClicked = event.whoClicked as Player
                val inventoryOwner = topInventory.holder

                if (inventoryOwner !is HumanEntity) {
                    return
                }

                if (whoClicked.hasMetadata("INVSEE")) {
                    event.isCancelled = true
                    refreshPlayer = whoClicked
                }

            }

        }

        refreshPlayer ?: return

        Bukkit.getScheduler().scheduleSyncDelayedTask(LuckyEssentials.instance, refreshPlayer::updateInventory, 1)

    }

    @EventHandler
    fun onInventoryClose(event: InventoryCloseEvent) {
        val topInventory = event.view.topInventory
        val inventoryType = topInventory.type
        val player = event.player as Player
        val refreshPlayer: Player

        when (inventoryType) {
            InventoryType.PLAYER -> {
                if (player.hasMetadata("INVSEE"))
                    player.removeMetadata("INVSEE")

                refreshPlayer = player
            }

            InventoryType.CHEST -> {
                val inventoryOwner = topInventory.holder

                if (inventoryOwner !is Player)
                    return

                if (player.hasMetadata("INVSEE"))
                    player.removeMetadata("INVSEE")

                refreshPlayer = player

            }

            else -> return

        }

        Bukkit.getScheduler().scheduleSyncDelayedTask(LuckyEssentials.instance, refreshPlayer::updateInventory, 1)

    }

}