package dev.luckynetwork.id.lyrams.extensions

internal fun Array<out String>.asString(): String {

    val sb = StringBuilder()

    for (arg in this)
        sb.append(arg).append(" ")

    return sb.toString()

}