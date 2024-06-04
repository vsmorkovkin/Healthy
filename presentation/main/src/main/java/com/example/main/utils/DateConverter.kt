package com.example.main.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateConverter {

    private const val DATE_ENTITY_FORMAT = "yyyy-MM-dd"

    private const val INPUT_FORMAT = "yyyy-MM-dd"
    private const val OUTPUT_FORMAT_HOME = "d MMMM, E"
    private const val OUTPUT_FORMAT_ACTIVITY_BY_DAY = "d MMMM yyyy, EEE"
    private const val OUTPUT_FORMAT_NUTRITION = "d MMMM, E"


    fun dateToDateEntity(date: Date) : String {
        val outputFormat = SimpleDateFormat(DATE_ENTITY_FORMAT, Locale("ru"))
        return outputFormat.format(date)
    }

    fun dateToHomeUi(currentDate: Date): String {
        val dateFormat = SimpleDateFormat(OUTPUT_FORMAT_HOME, Locale("ru", "RU"))
        return dateFormat.format(currentDate)
    }

    fun dateEntityToActivityByDayUi(entityDate: String): String = formatDateString(entityDate, OUTPUT_FORMAT_ACTIVITY_BY_DAY)

    fun dateEntityToNutritionUi(entityDate: String): String = formatDateString(entityDate, OUTPUT_FORMAT_NUTRITION)

    private fun formatDateString(inputDate: String, outputFormatString: String): String {
        // Parse input date
        val inputFormat = SimpleDateFormat(INPUT_FORMAT, Locale.getDefault())
        val date = inputFormat.parse(inputDate) ?: return inputDate

        // Create calendar for formatting
        val calendar = Calendar.getInstance()
        calendar.time = date

        // Format to output
        val outputFormat = SimpleDateFormat(outputFormatString, Locale("ru"))
        return outputFormat.format(calendar.time)
    }

}