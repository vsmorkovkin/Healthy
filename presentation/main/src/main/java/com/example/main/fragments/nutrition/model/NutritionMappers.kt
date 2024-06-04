package com.example.main.fragments.nutrition.model

import com.example.main.fragments.activity_by_day.model.toUi
import com.example.nutrition.entity.MealEntity
import com.example.nutrition.entity.NutritionWithMealsEntity

fun MealEntity.toUi(): MealUi {
    return MealUi(
        datetimeOfCreation, title, nutritionEntity.toUi()
    )
}

fun NutritionWithMealsEntity.toUi(): NutritionWithMealsUi {
    return NutritionWithMealsUi(
        totalNutrition.toUi(),
        mealsList.map { it.toUi() }
    )
}