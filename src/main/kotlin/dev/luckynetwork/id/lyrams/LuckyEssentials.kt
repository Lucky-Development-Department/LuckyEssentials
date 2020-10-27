package dev.luckynetwork.id.lyrams

import com.google.common.reflect.ClassPath
import dev.luckynetwork.id.lyrams.listeners.PlayerListeners
import dev.luckynetwork.id.lyrams.listeners.trolls.TrollPlayerListeners
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.utils.BetterCommand
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class LuckyEssentials : JavaPlugin() {

    companion object {
        lateinit var instance: LuckyEssentials
        var isChatLocked: Boolean = false
    }

    override fun onEnable() {
        if (Bukkit.getPluginManager().getPlugin("LuckyInjector") == null &&
            Bukkit.getPluginManager().getPlugin("KtLoader") == null
        )
            Bukkit.getLogger().warning("LuckyInjector or KtLoader not found! Plugin might not load!")

        instance = this

        Config.init(this)

        registerCommands()
        registerListeners()
    }

    override fun onDisable() {
        Bukkit.getScheduler().cancelTasks(this)
    }

    @Suppress("UnstableApiUsage")
    private fun registerCommands() {
        logger.info("Registering commands...")

        var total = 0
        var count = 0
        val classPath = ClassPath.from(javaClass.classLoader)
        for (classInfo in classPath.getTopLevelClassesRecursive("dev.luckynetwork.id.lyrams.commands")) {
            val commandClass = Class.forName(classInfo.name)
            try {
                val commandObject = commandClass.newInstance()
                if (commandObject is BetterCommand) {
                    // also disables troll commands if trolling id disabled in the config
                    if ((commandObject.javaClass.name.contains("features.trolls") && !Config.trollEnabled) ||
                        Config.disabledCommands.contains(commandObject.name)
                    ) {
                        ++total
                        continue
                    }

                    commandObject.register(this)
                    ++total
                    ++count
                }
            } catch (_: InstantiationException) {
            }
        }

        logger.info("Successfully registered $count out of $total commands!")
    }

    private fun registerListeners() {
        val pluginManager = server.pluginManager

        pluginManager.registerEvents(PlayerListeners(), this)
        pluginManager.registerEvents(TrollPlayerListeners(), this)
    }

}