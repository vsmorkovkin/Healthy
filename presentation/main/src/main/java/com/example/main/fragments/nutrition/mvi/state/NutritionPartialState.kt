package com.example.main.fragments.nutrition.mvi.state

import com.example.common.mvi.MviPartialState
import com.example.main.fragments.activity_by_day.model.NutritionUi
import com.example.main.fragments.nutrition.model.MealUi

sealed interface NutritionPartialState : MviPartialState {
    data class DateLoaded(val date: String): NutritionPartialState
    data class NutritionLoaded(
        val nutritionUi: NutritionUi,
        val mealsUi: List<MealUi>
    ): NutritionPartialState
}

