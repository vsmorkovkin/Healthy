package com.example.main.fragments.activity_by_day.mvi.intent

import com.example.common.mvi.MviIntent

sealed interface ActivityByDayIntent : MviIntent {
    data class GetActivityWithNutritionByDayIntent(val date: String): ActivityByDayIntent
    data object OpenSelectDateDialogIntent: ActivityByDayIntent
}