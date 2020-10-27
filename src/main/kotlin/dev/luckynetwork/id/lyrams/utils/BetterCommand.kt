package dev.luckynetwork.id.lyrams.utils

import dev.luckynetwork.id.lyrams.LuckyEssentials
import org.bukkit.command.Command
import org.bukkit.command.CommandMap

@Suppress("JoinDeclarationAndAssignment")
abstract class BetterCommand(
    command: String,
    aliases: List<String>?
) : Command(command) {

    private val commandName: String
    private val commandAliases: List<String>?

    fun register(plugin: LuckyEssentials) {
        val executorClass = Class.forName(this.javaClass.name)
        val commandObject = executorClass.newInstance()

        if (commandObject is Command) {
            if (commandAliases != null) commandObject.aliases = commandAliases
            registerCommand(plugin, commandObject)
        }
    }

    constructor(command: String) : this(
        command,
        null
    )

    constructor(command: String, vararg aliases: String) : this(
        command,
        aliases.toList()
    )

    init {
        this.commandName = command.toLowerCase()
        this.commandAliases = aliases
    }
}

var commandMap: CommandMap? = null
fun registerCommand(plugin: LuckyEssentials, command: Command) {
    if (commandMap == null) {
        val field = plugin.server.javaClass.getDeclaredField("commandMap")
        field.isAccessible = true
        commandMap = (field.get(plugin.server) as CommandMap)
    }

    commandMap!!.register(plugin.description.name, command)
    plugin.logger.info("Registered command '${command.name}'")
}