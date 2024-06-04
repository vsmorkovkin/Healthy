package com.example.main.fragments.nutrition.mvi.effect

import com.example.common.mvi.MviEffect

sealed interface NutritionEffect : MviEffect {
    data object ShowAddMealDialog : NutritionEffect
}