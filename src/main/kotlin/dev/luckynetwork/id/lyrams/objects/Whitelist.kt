package dev.luckynetwork.id.lyrams.objects

import dev.luckynetwork.id.lyrams.LuckyEssentials

object Whitelist {

    private var nullArray: ArrayList<String>? = null

    var whitelistCache = ArrayList<String>()
    var whitelistMessage = "You are not whitelisted!"
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
        enabled = LuckyEssentials.whitelistData.getBoolean("whitelist.enabled", false)
        whitelistMessage = LuckyEssentials.whitelistData.getString("whitelist.kick_message", "You are not whitelisted!")
        whitelistCache = LuckyEssentials.whitelistData.getStringList("whitelist.whitelisted") as ArrayList<String>
    }

    fun save() {
        LuckyEssentials.whitelistData.set("whitelist.enabled", enabled)
        LuckyEssentials.whitelistData.set("whitelist.whitelisted", whitelistCache)

        LuckyEssentials.whitelistData.save(LuckyEssentials.whitelistFile)
    }
}