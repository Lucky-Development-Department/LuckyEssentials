package dev.luckynetwork.id.lyrams.objects

import dev.luckynetwork.id.lyrams.LuckyEssentials
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

object Whitelist {

    var whitelistCache = ArrayList<String>()
    var kickMessage = "You are not whitelisted!"
    var enabled = false

    fun add(name: String, save: Boolean = true): Boolean {
        var success = false

        if (!whitelistCache.contains(name.toLowerCase())) {
            whitelistCache.add(name.toLowerCase())
            success = true
        }

        if (save)
            save()
        return success
    }

    fun remove(name: String, save: Boolean = true): Boolean {
        var success = false

        if (whitelistCache.contains(name.toLowerCase())) {
            whitelistCache.remove(name.toLowerCase())
            success = true
        }

        if (save)
            save()
        return success
    }

    fun toggle(boolean: Boolean?): Boolean {
        enabled = boolean ?: !enabled

        save()
        return enabled
    }

    fun list(): List<String> {
        return whitelistCache
    }

    fun isWhitelisted(name: String): Boolean {
        return whitelistCache.contains(name)
    }

    fun clear() {
        whitelistCache.clear()

        save()
    }

    fun reload() {
        LuckyEssentials.whitelistFile = File(LuckyEssentials.instance.dataFolder, "whitelist.yml")
        if (!LuckyEssentials.whitelistFile.exists())
            LuckyEssentials.instance.saveResource("whitelist.yml", false)
        LuckyEssentials.whitelistData = YamlConfiguration.loadConfiguration(LuckyEssentials.whitelistFile)

        enabled = LuckyEssentials.whitelistData.getBoolean("whitelist.enabled")
        kickMessage = LuckyEssentials.whitelistData.getString("whitelist.kick_message")
        whitelistCache = LuckyEssentials.whitelistData.getStringList("whitelist.whitelisted") as ArrayList<String>
    }

    fun save() {
        LuckyEssentials.whitelistData.set("whitelist.enabled", enabled)
        LuckyEssentials.whitelistData.set("whitelist.kick_message", kickMessage)
        LuckyEssentials.whitelistData.set("whitelist.whitelisted", whitelistCache)

        LuckyEssentials.whitelistData.save(LuckyEssentials.whitelistFile)
    }
}