package com.example.main.fragments.activity_by_day.model

data class ActivityUi(
    val date: String,
    val nutrition: NutritionUi,
    val stepsNumber: Int,
    val waterIntake: Float,
    val sleepTime: Float,
    val weight: Float
)
