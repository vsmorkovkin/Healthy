package com.example.main.fragments.nutrition.mvi.state

import com.example.common.mvi.MviPartialState
import com.example.main.fragments.nutrition.model.NutritionWithMealsUi

sealed interface NutritionPartialState : MviPartialState {
    data class DateLoaded(val date: String) : NutritionPartialState
    data class NutritionWithMealsLoaded(val nutritionWithMealsUi : NutritionWithMealsUi) : NutritionPartialState
}