package com.example.main.fragments.nutrition.mvi.intent

import com.example.common.mvi.MviIntent
import com.example.nutrition.entity.MealEntity

sealed interface NutritionIntent : MviIntent {
    data class DateReceivedFromArgs(val date: String) : NutritionIntent
    data class GetNutritionWithMealsByDate(val date: String) : NutritionIntent
    data class AddMealByDate(val date: String, val mealEntity: MealEntity) : NutritionIntent
    data class DeleteMealByDate(val date: String, val mealDateTimeOfCreation: Long) : NutritionIntent
    data object OpenAddMealDialog : NutritionIntent
}