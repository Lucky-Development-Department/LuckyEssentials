package dev.luckynetwork.id.lyrams.extensions

fun String.colorizeTrueOrFalse(): String =
    when {
        this.equals("true", true) -> "§a$this"
        this.equals("false", true) -> "§c$this"
        else -> this
    }
