package dev.luckynetwork.id.lyrams.objects

import dev.luckynetwork.id.lyrams.LuckyEssentials
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

    fun getSlots(): Int {
        return amount
    }

    fun convert() {
        if (amount != -1)
            return

        val logger = Bukkit.getLogger()
        val pluginManager = Bukkit.getPluginManager()
        logger.info("[MYSlots Converter] Attempting to convert slots data from MYSlots")

        if (!pluginManager.isPluginEnabled("MYSlots")) {
            logger.info("[MYSlots Converter] MYSlots not found!")
            return
        }

        logger.info("[MYSlots Converter] MYSlots found!")

        val file = File("plugins/MYSlots/config.yml")
        if (!file.exists())
            return
        val mySlotsConfig = YamlConfiguration.loadConfiguration(file)


        logger.info("[MYSlots Converter] Converting data...")


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

    fun reload() {
        LuckyEssentials.slotsFile = File(LuckyEssentials.instance.dataFolder, "slots.yml")
        if (!LuckyEssentials.slotsFile.exists())
            LuckyEssentials.instance.saveResource("slots.yml", false)
        LuckyEssentials.slotsData = YamlConfiguration.loadConfiguration(LuckyEssentials.slotsFile)

        enabled = LuckyEssentials.slotsData.getBoolean("slots.enabled")
        fullMessage = LuckyEssentials.slotsData.getString("slots.kick_message")
        amount = LuckyEssentials.slotsData.getInt("slots.max_player")
    }

    fun save() {
        LuckyEssentials.slotsData.set("slots.enabled", enabled)
        LuckyEssentials.slotsData.set("slots.kick_message", fullMessage)
        LuckyEssentials.slotsData.set("slots.max_player", amount)

        LuckyEssentials.slotsData.save(LuckyEssentials.slotsFile)
    }
}