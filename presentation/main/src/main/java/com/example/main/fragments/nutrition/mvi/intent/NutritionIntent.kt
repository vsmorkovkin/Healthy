package com.example.main.fragments.nutrition.mvi.intent

import com.example.common.mvi.MviIntent
import com.example.nutrition.entity.MealEntity

sealed interface NutritionIntent : MviIntent {
    data class DateReceivedFromArgs(val date: String) : NutritionIntent
    data object GetNutritionWithMealsByDate : NutritionIntent
    data class AddMealByDate(val mealEntity: MealEntity) : NutritionIntent
    data object DeleteMealByDate : NutritionIntent
    data object OpenAddMealDialog : NutritionIntent
}