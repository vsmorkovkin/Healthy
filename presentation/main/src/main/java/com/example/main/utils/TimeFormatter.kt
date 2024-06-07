package com.example.main.utils

object TimeFormatter {

    // ^ - начало строки
    // ([01]?[0-9]|2[0-3]) - проверяет часы:
    // [01]?[0-9] - часы могут быть от 00 до 09 или от 10 до 19 (одна или две цифры, первая цифра 0 или 1)
    // | - или
    // 2[0-3] - часы могут быть от 20 до 23
    // : - разделитель между часами и минутами
    // [0-5][0-9] - проверяет минуты (от 00 до 59)
    // $ - конец строки
    fun isValidTimeString(time: String): Boolean {
        val timePattern = Regex("^([01]?[0-9]|2[0-3]):[0-5][0-9]$")
        return timePattern.matches(time)
    }
}