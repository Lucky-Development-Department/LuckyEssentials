package dev.luckynetwork.id.lyrams

import dev.luckynetwork.id.lyrams.commands.LuckyEssentialsCMD
import dev.luckynetwork.id.lyrams.commands.features.*
import dev.luckynetwork.id.lyrams.listeners.PlayerListeners
import dev.luckynetwork.id.lyrams.objects.Slots
import dev.luckynetwork.id.lyrams.objects.Whitelist
import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class LuckyEssentials : JavaPlugin() {

    companion object {
        lateinit var instance: LuckyEssentials

        lateinit var whitelistFile: File
        lateinit var whitelistData: FileConfiguration
        lateinit var slotsFile: File
        lateinit var slotsData: FileConfiguration
    }


    override fun onEnable() {
        if (Bukkit.getPluginManager().getPlugin("LuckyInjector") == null) {
            Bukkit.getLogger().warning("LuckyInjector not found! Plugin might not load!")
        }

        instance = this
        whitelistFile = File(this.dataFolder, "whitelist.yml")
        slotsFile = File(this.dataFolder, "slots.yml")

        if (!this.dataFolder.exists()) {
            this.dataFolder.mkdirs()
            this.saveResource("whitelist.yml", false)
            this.saveResource("slots.yml", false)
        }

        whitelistData = YamlConfiguration.loadConfiguration(whitelistFile)
        slotsData = YamlConfiguration.loadConfiguration(slotsFile)

        registerCommands()
        registerListeners()

        Bukkit.getScheduler().runTaskLater(this, {
            Whitelist.reload()
            Slots.convert()
            Slots.reload()
        }, 1L)
    }

    override fun onDisable() {
        Bukkit.getScheduler().cancelTasks(this)
    }

    private fun registerCommands() {
        getCommand("gamemode").executor = GamemodeCMD()
        getCommand("feed").executor = FeedCMD()
        getCommand("heal").executor = HealCMD()
        getCommand("fly").executor = FlyCMD()
        getCommand("give").executor = GiveCMD()
        getCommand("teleport").executor = TeleportCMD()
        getCommand("clear").executor = ClearCMD()
        getCommand("editsign").executor = EditSignCMD()
        getCommand("smite").executor = SmiteCMD()
        getCommand("explode").executor = ExplodeCMD()
        getCommand("invsee").executor = InvseeCMD()
        getCommand("more").executor = MoreCMD()
        getCommand("speed").executor = SpeedCMD()
        getCommand("kickall").executor = KickAllCMD()
        getCommand("fix").executor = FixCMD()
        getCommand("ewhitelist").executor = WhitelistCMD()
        getCommand("slots").executor = SlotsCMD()
        getCommand("god").executor = GodCMD()
        getCommand("getpos").executor = GetPosCMD()
        getCommand("top").executor = TopCMD()
        getCommand("sudo").executor = SudoCMD()
        getCommand("enchant").executor = EnchantCMD()
        getCommand("luckyessentials").executor = LuckyEssentialsCMD()
    }

    private fun registerListeners() {
        val pluginManager = server.pluginManager

        pluginManager.registerEvents(PlayerListeners(), this)
    }

}