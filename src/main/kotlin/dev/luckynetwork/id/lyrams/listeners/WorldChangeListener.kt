package dev.luckynetwork.id.lyrams.listeners

import dev.luckynetwork.id.lyrams.extensions.checkPermissionSilent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChangedWorldEvent

class WorldChangeListener : Listener {

    @EventHandler
    fun onWorldChange(event: PlayerChangedWorldEvent) {
        val player = event.player

        if (player.checkPermissionSilent("keepfly"))
            return

        if (player.allowFlight)
            player.allowFlight = false

    }

}