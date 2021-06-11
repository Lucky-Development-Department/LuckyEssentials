package dev.luckynetwork.id.lyrams.objects

object Whitelist {

    var whitelistCache = ArrayList<String>()
    var kickMessage = "You are not whitelisted!"
    var enabled = false

    fun add(name: String, save: Boolean = true): Boolean {
        var success = false

        if (!whitelistCache.contains(name.lowercase())) {
            whitelistCache.add(name.lowercase())
            success = true
        }

        if (save)
            save()
        return success
    }

    fun remove(name: String, save: Boolean = true): Boolean {
        var success = false

        if (whitelistCache.contains(name.lowercase())) {
            whitelistCache.remove(name.lowercase())
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

    fun list(): List<String> =
        whitelistCache


    fun isWhitelisted(name: String): Boolean =
        whitelistCache.contains(name)


    fun clear() {
        whitelistCache.clear()
        save()
    }

    fun reload() =
        Config.reloadWhitelist()

    fun save() {
        Config.whitelistData.set("whitelist.enabled", enabled)
        Config.whitelistData.set("whitelist.kick_message", kickMessage)
        Config.whitelistData.set("whitelist.whitelisted", whitelistCache)

        Config.whitelistData.save(Config.whitelistFile)
    }

}