package dev.luckynetwork.id.lyrams.objects

import org.bukkit.Bukkit
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

object Slots {

    var amount = -1
    var fullMessage = "Server full!"
    var enabled = false

    fun set(int: Int) {
        amount = int
        save()
    }

    fun toggle(boolean: Boolean?): Boolean {
        enabled = boolean ?: !enabled
        save()
        return enabled
    }

    fun getSlots(): Int = amount

    fun convert() {
        if (amount != -1)
            return

        val logger = Bukkit.getLogger()
        val pluginManager = Bukkit.getPluginManager()
        logger.info("[MYSlots Converter] Attempting to convert slots data from MYSlots")

        if (!pluginManager.isPluginEnabled("MYSlots"))
            return logger.info("[MYSlots Converter] MYSlots not found!")

        logger.info("[MYSlots Converter] MYSlots found!")

        val file = File("plugins/MYSlots/config.yml")
        if (!file.exists())
            return

        logger.info("[MYSlots Converter] Converting data...")

        val mySlotsConfig = YamlConfiguration.loadConfiguration(file)
        enabled = mySlotsConfig.getBoolean("Enable")
        fullMessage = "Server full!"
        amount = mySlotsConfig.getInt("Slots")
        save()

        logger.info("[MYSlots Converter] Disabling MySlots...")

        mySlotsConfig.set("Enable", false)
        mySlotsConfig.save(file)

        pluginManager.disablePlugin(pluginManager.getPlugin("MYSlots"))
        logger.info("[MYSlots Converter] MySlots Disabled!")
        logger.info("[MYSlots Converter] Converting success!")

    }

    fun reload() =
        Config.reloadSlots()

    fun save() {
        Config.slotsData.set("slots.enabled", enabled)
        Config.slotsData.set("slots.kick_message", fullMessage)
        Config.slotsData.set("slots.max_player", amount)

        Config.slotsData.save(Config.slotsFile)

    }
}