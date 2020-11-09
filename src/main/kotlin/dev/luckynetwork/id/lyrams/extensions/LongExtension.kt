package dev.luckynetwork.id.lyrams.extensions

import java.util.*

fun Long.toDate(): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    calendar.timeZone = TimeZone.getTimeZone("Asia/Jakarta")

    val mYear = calendar[Calendar.YEAR]
    val mMonth = calendar[Calendar.MONTH]
    val mDay = calendar[Calendar.DAY_OF_MONTH]
    val mAmPm =
        if (calendar[Calendar.AM_PM] == 1) "PM"
        else "AM"
    val mHour = calendar[Calendar.HOUR]
    val mMinute = calendar[Calendar.MINUTE]
    val mSecond = calendar[Calendar.SECOND]

    return "$mDay-$mMonth-$mYear $mHour:$mMinute:$mSecond $mAmPm"
}