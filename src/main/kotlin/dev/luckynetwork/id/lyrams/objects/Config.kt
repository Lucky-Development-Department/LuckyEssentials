package dev.luckynetwork.id.lyrams.objects

import dev.luckynetwork.id.lyrams.LuckyEssentials
import dev.luckynetwork.id.lyrams.extensions.colorize
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

object Config {

    private lateinit var plugin: LuckyEssentials

    lateinit var prefix: String
    lateinit var messagesFile: File
    lateinit var messagesData: FileConfiguration
    lateinit var whitelistFile: File
    lateinit var whitelistData: FileConfiguration
    lateinit var slotsFile: File
    lateinit var slotsData: FileConfiguration
    lateinit var disabledCommands: List<String>

    var trollEnabled: Boolean = true

    fun init(plugin: LuckyEssentials) {
        this.plugin = plugin

        messagesFile = File(plugin.dataFolder, "messages.yml")
        whitelistFile = File(plugin.dataFolder, "whitelist.yml")
        slotsFile = File(plugin.dataFolder, "slots.yml")

        if (!plugin.dataFolder.exists()) {
            plugin.dataFolder.mkdirs()
            plugin.saveResource("messages.yml", false)
            plugin.saveResource("whitelist.yml", false)
            plugin.saveResource("slots.yml", false)
        }

        messagesData = YamlConfiguration.loadConfiguration(messagesFile)
        whitelistData = YamlConfiguration.loadConfiguration(whitelistFile)
        slotsData = YamlConfiguration.loadConfiguration(slotsFile)

        reloadAll()
    }

    fun reloadAll() {
        reloadConfig()
        reloadMessages()
        reloadSlots()
        reloadWhitelist()
    }

    private fun reloadConfig() {
        val configFile = File(plugin.dataFolder, "config.yml")
        if (!configFile.exists())
            plugin.saveResource("config.yml", false)

        plugin.reloadConfig()
        trollEnabled = plugin.config.getBoolean("troll-features", true)
        disabledCommands = plugin.config.getStringList("disabled-commands")
    }

    private fun reloadMessages() {
        messagesFile = File(plugin.dataFolder, "messages.yml")
        if (!messagesFile.exists())
            plugin.saveResource("messages.yml", false)

        messagesData = YamlConfiguration.loadConfiguration(messagesFile)
        prefix = messagesData.getString("prefix", "&e&lLuckyEssentials &a/").colorize()
    }

    fun reloadSlots() {
        Slots.convert()

        slotsFile = File(plugin.dataFolder, "slots.yml")
        if (!slotsFile.exists())
            plugin.saveResource("slots.yml", false)

        slotsData = YamlConfiguration.loadConfiguration(slotsFile)

        Slots.enabled = slotsData.getBoolean("slots.enabled")
        Slots.fullMessage = slotsData.getString("slots.kick_message")
        Slots.amount = slotsData.getInt("slots.max_player")
    }

    fun reloadWhitelist() {
        whitelistFile = File(plugin.dataFolder, "whitelist.yml")
        if (!whitelistFile.exists())
            plugin.saveResource("whitelist.yml", false)

        whitelistData = YamlConfiguration.loadConfiguration(whitelistFile)

        Whitelist.enabled = whitelistData.getBoolean("whitelist.enabled")
        Whitelist.kickMessage = whitelistData.getString("whitelist.kick_message")
        Whitelist.whitelistCache = whitelistData.getStringList("whitelist.whitelisted") as ArrayList<String>
    }

}