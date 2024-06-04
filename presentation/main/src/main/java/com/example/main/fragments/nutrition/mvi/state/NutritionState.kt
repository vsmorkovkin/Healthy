package com.example.main.fragments.nutrition.mvi.state

import com.example.common.mvi.MviState
import com.example.main.fragments.activity_by_day.model.NutritionUi
import com.example.main.fragments.nutrition.model.MealUi

data class NutritionState(
    val date: String,
    val nutritionUi: NutritionUi?,
    val mealsUi: List<MealUi>
) : MviState
