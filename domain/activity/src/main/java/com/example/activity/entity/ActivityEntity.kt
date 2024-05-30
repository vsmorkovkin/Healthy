package com.example.activity.entity

import com.example.nutrition.entity.NutritionEntity

data class ActivityEntity(
    val day: String,
    val nutrition: NutritionEntity,
    val stepsNumber: Int,
    val waterIntake: Float,
    val sleepTime: Float,
    val weight: Float
)