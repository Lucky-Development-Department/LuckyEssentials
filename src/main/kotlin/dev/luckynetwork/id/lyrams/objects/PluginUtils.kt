package dev.luckynetwork.id.lyrams.objects

import com.google.common.base.Joiner
import dev.luckynetwork.id.lyrams.LuckyEssentials
import dev.luckynetwork.id.lyrams.extensions.disable
import dev.luckynetwork.id.lyrams.extensions.enable
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.PluginCommand
import org.bukkit.command.SimpleCommandMap
import org.bukkit.event.Event
import org.bukkit.plugin.*
import java.io.File
import java.io.IOException
import java.lang.reflect.Field
import java.net.URLClassLoader
import java.util.*

/**
 * @credit Plugman
 * @link https://github.com/r-clancy/PlugMan
 */
object PluginUtils {

    /**
     * gets a plugin by its name
     */
    fun getPluginByName(name: String): Plugin? {

        for (plugin in Bukkit.getPluginManager().plugins) {
            if (plugin.name.equals(name, true))
                return plugin;
        }

        return null
    }

    fun getFormattedName(plugin: Plugin, includeVersions: Boolean): String {
        val color =
            if (plugin.isEnabled)
                "§a"
            else
                "§c"

        var pluginName = color + plugin.name

        if (includeVersions)
            pluginName += " (" + plugin.description.version.toString() + ")"

        return pluginName
    }

    fun getUsages(plugin: Plugin): String {
        val parsedCommands = ArrayList<String>()

        val commands = plugin.description.commands

        if (commands != null) {
            val commandsIt = commands.entries.iterator()
            while (commandsIt.hasNext()) {
                val thisEntry = commandsIt.next() as Map.Entry<*, *>
                parsedCommands.add(thisEntry.key as String)
            }
        }

        return if (parsedCommands.isEmpty()) "No commands registered." else Joiner.on(", ").join(parsedCommands)
    }

    fun getPluginNames(fullName: Boolean): List<String> {
        val plugins = ArrayList<String>()

        for (plugin in Bukkit.getPluginManager().plugins) {
            plugins.add(
                if (fullName)
                    plugin.description.fullName
                else
                    plugin.name
            )
        }

        return plugins
    }

    fun findByCommand(command: String): List<String> {
        val plugins: MutableList<String> = ArrayList()

        for (plugin in Bukkit.getPluginManager().plugins) {

            // Map of commands and their attributes.
            val commands = plugin.description.commands

            if (commands != null) {

                for (commandNext in commands.entries) {

                    // Plugin name matches - return.
                    if (commandNext.key.equals(command, ignoreCase = true)) {
                        plugins.add(plugin.name)
                        continue
                    }

                    for (attributeNext in commandNext.value.entries) {

                        // Has an alias attribute.
                        if (attributeNext.key == "aliases") {

                            val aliases = attributeNext.value

                            if (aliases is String) {
                                if (aliases.equals(command, ignoreCase = true))
                                    plugins.add(plugin.name)

                            } else {

                                // Cast to a List of Strings.
                                val array = aliases as List<*>

                                // Check for matches here.
                                for (str in array)
                                    if (str is String && str.equals(command, true))
                                        plugins.add(plugin.name)

                            }
                        }
                    }
                }
            }
        }

        // No matches.
        return plugins
    }

    /**
     * loads a plugin
     */
    fun load(plugin: Plugin) {
        load(plugin.name)
    }

    /**
     * loads a plugin
     */
    fun load(name: String): Boolean {

        val plugin: Plugin?

        val pluginDir = File("plugins")
        var pluginFile = File(pluginDir, "${name}.jar")

        if (!pluginFile.isFile) {
            for (file in pluginDir.listFiles()) {
                if (file.name.endsWith(".jar")) {
                    try {
                        val desc: PluginDescriptionFile =
                            LuckyEssentials.instance.pluginLoader.getPluginDescription(file)
                        if (desc.name.equals(name, ignoreCase = true)) {
                            pluginFile = file
                            break
                        }
                    } catch (e: InvalidDescriptionException) {
                        e.printStackTrace()
                        return false
                    }
                }
            }
        }

        try {
            plugin = Bukkit.getPluginManager().loadPlugin(pluginFile)
        } catch (e: InvalidDescriptionException) {
            e.printStackTrace()
            return false
        } catch (e: InvalidPluginException) {
            e.printStackTrace()
            return false
        }

        plugin.onLoad()
        plugin.enable()

        return true
    }

    /**
     * unloads a plugin
     */
    fun unload(plugin: Plugin): Boolean {
        val name = plugin.name
        val pluginManager: PluginManager = Bukkit.getPluginManager()
        val commandMap: SimpleCommandMap?

        val plugins: MutableList<Plugin>
        val names: MutableMap<String?, Plugin?>?
        val commands: MutableMap<String?, Command>?
        var listeners: Map<Event?, SortedSet<RegisteredListener>>? = null

        var reloadlisteners = true

        plugin.disable()

        try {
            val pluginsField: Field = pluginManager.javaClass.getDeclaredField("plugins")
            pluginsField.isAccessible = true
            plugins = pluginsField.get(pluginManager) as MutableList<Plugin>

            val lookupNamesField: Field = pluginManager.javaClass.getDeclaredField("lookupNames")
            lookupNamesField.isAccessible = true
            names = lookupNamesField.get(pluginManager) as MutableMap<String?, Plugin?>?

            try {
                val listenersField: Field = pluginManager.javaClass.getDeclaredField("listeners")
                listenersField.isAccessible = true
                listeners = listenersField.get(pluginManager) as Map<Event?, SortedSet<RegisteredListener>>?
            } catch (e: Exception) {
                reloadlisteners = false
            }

            val commandMapField: Field = pluginManager.javaClass.getDeclaredField("commandMap")
            commandMapField.isAccessible = true
            commandMap = commandMapField.get(pluginManager) as SimpleCommandMap?

            val knownCommandsField: Field = SimpleCommandMap::class.java.getDeclaredField("knownCommands")
            knownCommandsField.isAccessible = true
            commands = knownCommandsField.get(commandMap) as MutableMap<String?, Command>?

        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

        plugin.disable()

        if (plugins.contains(plugin))
            plugins.remove(plugin)

        if (names != null && names.containsKey(name))
            names.remove(name)

        if (listeners != null && reloadlisteners) {
            for (set in listeners.values) {
                val it = set.iterator()
                while (it.hasNext()) {
                    val value = it.next()
                    if (value.plugin === plugin) {
                        it.remove()
                    }
                }
            }
        }

        if (commandMap != null) {
            val it: MutableIterator<Map.Entry<String?, Command>> = commands!!.entries.iterator()
            while (it.hasNext()) {
                val entry: Map.Entry<String?, Command> = it.next()
                if (entry.value is PluginCommand) {
                    val command = entry.value as PluginCommand
                    if (command.plugin === plugin) {
                        command.unregister(commandMap)
                        it.remove()
                    }
                }
            }
        }

        // Attempt to close the classloader to unlock any handles on the plugin's jar file.
        val classLoader: ClassLoader = plugin.javaClass.classLoader

        if (classLoader is URLClassLoader) {
            try {
                val pluginField: Field = classLoader.javaClass.getDeclaredField("plugin")
                pluginField.isAccessible = true
                pluginField.set(classLoader, null)

                val pluginInitField: Field = classLoader.javaClass.getDeclaredField("pluginInit")
                pluginInitField.isAccessible = true
                pluginInitField.set(classLoader, null)
            } catch (ex: Exception) {
                ex.printStackTrace()
                return false
            }

            try {
                classLoader.close()
            } catch (ex: IOException) {
                ex.printStackTrace()
                return false
            }
        }

        // Will not work on processes started with the -XX:+DisableExplicitGC flag, but lets try it anyway.
        // This tries to get around the issue where Windows refuses to unlock jar files that were previously loaded into the JVM.
        System.gc()

        return true
    }

}