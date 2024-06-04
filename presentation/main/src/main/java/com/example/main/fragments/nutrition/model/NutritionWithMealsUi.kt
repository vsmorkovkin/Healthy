package com.example.main.fragments.nutrition.model

import com.example.main.fragments.activity_by_day.model.NutritionUi

data class NutritionWithMealsUi(
    val totalNutrition: NutritionUi = NutritionUi(),
    val mealsList: List<MealUi> = emptyList()
)