package dev.luckynetwork.id.lyrams.extensions

import dev.luckynetwork.id.lyrams.objects.PluginUtils
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin

fun Plugin.enable() {
    if (!this.isEnabled)
        Bukkit.getPluginManager().enablePlugin(this)
}

fun Plugin.disable() {
    if (this.isEnabled)
        Bukkit.getPluginManager().disablePlugin(this)
}

fun Plugin.reload() {
    PluginUtils.unload(this)
    PluginUtils.load(this)
}

fun Plugin.restart() {
    this.disable()
    this.enable()
}