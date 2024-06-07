package com.example.main.fragments.home.mvi.effect

import com.example.common.mvi.MviEffect

sealed interface HomeEffect : MviEffect {
    data object NavigateToNutritionFragment : HomeEffect
}