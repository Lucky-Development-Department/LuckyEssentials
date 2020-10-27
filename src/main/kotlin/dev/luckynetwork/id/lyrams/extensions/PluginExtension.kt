package dev.luckynetwork.id.lyrams.extensions

import dev.luckynetwork.id.lyrams.objects.PluginUtils
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin

/** enables [Plugin] */
internal fun Plugin.enable() {
    if (!this.isEnabled)
        Bukkit.getPluginManager().enablePlugin(this)
}

/** disables [Plugin] */
internal fun Plugin.disable() {
    if (this.isEnabled)
        Bukkit.getPluginManager().disablePlugin(this)
}

/** reloads [Plugin] */
internal fun Plugin.reload() {
    PluginUtils.unload(this)
    PluginUtils.load(this)
}

/** restarts [Plugin] */
internal fun Plugin.restart() {
    this.disable()
    this.enable()
}