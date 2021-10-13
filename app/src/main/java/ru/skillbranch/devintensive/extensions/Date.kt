package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.time.Duration
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

fun Date.humanizeDiff(date:Date=Date()): String {
    val diff: Long = date.getTime()-this.getTime()
    when(diff){
        in 0*SECOND..1* SECOND -> return "только что"
        in 1*SECOND..45*SECOND -> return "несколько секунд назад"
        in 45*SECOND..75*SECOND -> return "минуту назад"
        in 75*SECOND..45* MINUTE -> return (diff/MINUTE).toString()+ " минут назад"
        in 45* MINUTE..75*MINUTE -> return "час назад"
        in 75* MINUTE..22* HOUR -> return (diff/HOUR).toString()+" часов назад"
        in 22* HOUR ..26* HOUR -> return "день назад"
        in 26*HOUR..360* DAY -> return (diff/DAY).toString()+" дней назад"
        else -> return "более года назад"
    }
}
