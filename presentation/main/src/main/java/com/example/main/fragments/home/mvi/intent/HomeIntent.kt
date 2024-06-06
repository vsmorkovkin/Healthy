package com.example.main.fragments.home.mvi.intent

import com.example.common.mvi.MviIntent

sealed interface HomeIntent : MviIntent {
    data object GetCurrentDate : HomeIntent
    data object GetActivityWithNutrition : HomeIntent

    data class UpdateWaterIntakeInActivity(val waterIntake: Int) : HomeIntent
    data class UpdateSleepTimeInActivity(val bedtime: String, val wakeupTime: String) : HomeIntent
    data class UpdateWeightInActivity(val weight: Float) : HomeIntent

    data object OpenNutritionScreen : HomeIntent
}