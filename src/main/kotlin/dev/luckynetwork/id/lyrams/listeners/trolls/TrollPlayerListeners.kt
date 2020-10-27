package dev.luckynetwork.id.lyrams.listeners.trolls

import dev.luckynetwork.id.lyrams.LuckyEssentials
import dev.luckynetwork.id.lyrams.objects.Config
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerPickupItemEvent

class TrollPlayerListeners : Listener {

    @EventHandler
    fun onItemPickup(event: PlayerPickupItemEvent) {
        if (!Config.trollEnabled)
            return

        val player = event.player
        if (player.hasMetadata("NOPICKUP"))
            event.isCancelled = true
    }

    @EventHandler
    fun onDamage(event: EntityDamageEvent) {
        if (!Config.trollEnabled)
            return

        event.entity ?: return

        val victim = event.entity
        if (victim !is Player)
            return

        if (victim.hasMetadata("ONETAP"))
            event.damage = 100.0
    }

    @EventHandler
    fun onDamageByEntity(event: EntityDamageByEntityEvent) {
        if (!Config.trollEnabled)
            return

        event.entity ?: return
        event.damager ?: return

        val attacker = event.damager
        if (attacker.hasMetadata("NODAMAGE"))
            event.damage = 0.0
        if (attacker.hasMetadata("NOHIT"))
            event.isCancelled = true
    }

    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) {
        if (!Config.trollEnabled)
            return

        val player = event.player
        if (player.hasMetadata("NOPLACE"))
            event.isCancelled = true

        if (player.hasMetadata("FAKEPLACE")) {
            val block = event.block
            Bukkit.getScheduler().runTaskLater(LuckyEssentials.instance, {
                block.type = Material.AIR
            }, 17L)
        }
    }

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        if (!Config.trollEnabled)
            return

        val player = event.player
        if (player.hasMetadata("NOBREAK"))
            event.isCancelled = true
    }

    @EventHandler
    fun onInteract(event: PlayerInteractEvent) {
        if (!Config.trollEnabled)
            return

        val player = event.player
        if (player.hasMetadata("NOINTERACT")) {
            if (event.action != Action.RIGHT_CLICK_BLOCK || !event.isBlockInHand)
                event.isCancelled = true
        }
    }

}