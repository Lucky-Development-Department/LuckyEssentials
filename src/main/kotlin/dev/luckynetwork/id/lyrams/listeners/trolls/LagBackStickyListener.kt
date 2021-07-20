package dev.luckynetwork.id.lyrams.listeners.trolls

import dev.luckynetwork.id.lyrams.LuckyEssentials
import dev.luckynetwork.id.lyrams.objects.Config
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

class LagBackStickyListener : Listener {

    private val playerList: HashSet<Player> = HashSet();

    @EventHandler
    fun onMove(event: PlayerMoveEvent) {
        if (!Config.trollEnabled)
            return

        val player = event.player
        if (player.hasMetadata("LAGBACK")) {
            if (!playerList.contains(player)) {
                playerList.add(player)

                val location = player.location
                Bukkit.getScheduler().runTaskLater(LuckyEssentials.instance, {
                    player.teleport(location)
                    Bukkit.getScheduler().runTaskLater(LuckyEssentials.instance, {
                        playerList.remove(player)
                    }, (15L..30L).random())
                }, (15L..20L).random())
            }
        }

        if (player.hasMetadata("STICKY")) {
            val location = player.location
            val highest = location.world.getHighestBlockAt(location.blockX, location.blockZ)
            if (location.blockY > highest.y + 1.5) {
                player.teleport(Location(location.world, location.x, highest.y.toDouble(), location.z, location.yaw, location.pitch))
            }
        }
    }
}