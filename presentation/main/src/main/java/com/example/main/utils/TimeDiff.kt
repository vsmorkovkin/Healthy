package com.example.main.utils

import java.text.SimpleDateFormat
import java.util.Locale

object TimeDiff {

    fun calculateSleepHours(bedtime: String, wakeupTime: String): Float {
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val bedTimeDate = dateFormat.parse(bedtime)
        val wakeupTimeDate = dateFormat.parse(wakeupTime)

        val difference = wakeupTimeDate.time - bedTimeDate.time // diff in milliseconds

        return difference.toFloat() / (1000 * 60 * 60) // convert to hours
    }
}