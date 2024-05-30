package com.example.main.fragments.activity_by_day.model

import com.example.activity.entity.ActivityEntity
import com.example.nutrition.entity.NutritionEntity

fun ActivityEntity.toUi(): ActivityUi {
    return this.run {
        ActivityUi(
            date = day,
            nutrition = nutrition.toUi(),
            stepsNumber = stepsNumber,
            waterIntake = waterIntake,
            sleepTime = sleepTime,
            weight = weight
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