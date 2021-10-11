package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

const val SECOND = 1000L
const val MINUTE = 60* SECOND
const val HOUR = 60* MINUTE
const val DAY = 24* HOUR

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}

fun Date.format(pattern: String="HH:mm:ss dd.MM.yy"):String{
    var localPattern:String = pattern
    val dateFormat =  SimpleDateFormat(localPattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units:TimeUnits=TimeUnits.SECOND ):Date{
    var time = this.time
    time+= when (units) {
        TimeUnits.SECOND -> value* SECOND
        TimeUnits.MINUTE -> value* MINUTE
        TimeUnits.HOUR -> value* HOUR
        TimeUnits.DAY -> value* DAY
        else -> throw IllegalStateException("Invalid Error")
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(): String {
    return ""
}