package com.example.activity.model

import com.example.activity.entity.ActivityEntity

data class ActivityFirebaseModel(
    val day: String = "",
    val nutrition: NutritionFirebaseModel = NutritionFirebaseModel(),
    val stepsNumber: Int = 0,
    val waterIntake: Float = 0f,
    val sleepTime: Float = 0f,
    val weight: Float = 0f
) {
    fun toDomain() = ActivityEntity(
        day,
        nutrition.toDomain(),
        stepsNumber,
        waterIntake,
        sleepTime,
        weight
    )
}
