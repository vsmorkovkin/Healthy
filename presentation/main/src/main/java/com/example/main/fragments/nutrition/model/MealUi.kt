package com.example.main.fragments.nutrition.model

import com.example.main.fragments.activity_by_day.model.NutritionUi

data class MealUi(
    val dateTimeOfCreation: Long,
    val title: String,
    val nutritionUi: NutritionUi
)
