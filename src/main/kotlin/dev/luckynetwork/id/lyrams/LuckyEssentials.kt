package dev.luckynetwork.id.lyrams

import dev.luckynetwork.id.lyrams.commands.*
import dev.luckynetwork.id.lyrams.listeners.WorldChangeListener
import org.bukkit.plugin.java.JavaPlugin

class LuckyEssentials : JavaPlugin() {

    companion object {
        lateinit var instance: LuckyEssentials
    }


    override fun onEnable() {
        instance = this

        registerCommands()
        registerListeners()
    }

    override fun onDisable() {

    }

    private fun registerCommands() {
        getCommand("gamemode").executor = GamemodeCMD()
        getCommand("feed").executor = FeedCMD()
        getCommand("heal").executor = HealCMD()
        getCommand("fly").executor = FlyCMD()
        getCommand("give").executor = GiveCMD()
        getCommand("teleport").executor = TeleportCMD()
    }

    private fun registerListeners() {
        val pluginManager = server.pluginManager

        pluginManager.registerEvents(WorldChangeListener(), this)
    }

}