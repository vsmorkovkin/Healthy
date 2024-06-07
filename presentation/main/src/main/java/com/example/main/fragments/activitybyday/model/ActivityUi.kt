package com.example.main.fragments.activitybyday.model

data class ActivityUi(
    val date: String,
    val nutrition: NutritionUi,
    val stepsNumber: Int,
    val waterIntake: Float,
    val sleepTime: Float,
    val weight: Float
)
