package com.example.main.fragments.activity_by_day.model

import com.example.activity.entity.ActivityWithNutritionEntity
import com.example.nutrition.entity.NutritionEntity

fun ActivityWithNutritionEntity.toUi(
    formatDate: (dateString: String) -> String = { s -> s }
): ActivityUi {
    return this.run {
        ActivityUi(
            date = "",//formatDate(day),
            nutrition = nutrition.toUi(),
            stepsNumber = activityEntity.stepsNumber,
            waterIntake = activityEntity.waterIntake.toFloat(),
            sleepTime = activityEntity.sleepTime,
            weight = activityEntity.weight
        )
    }
}

fun NutritionEntity.toUi(): NutritionUi {
    return this.run {
        NutritionUi(
            calories, proteins, fats, carbs
        )
    }
}