package com.example.main.fragments.nutrition.mvi.store

import com.example.common.mvi.MviReducer
import com.example.main.fragments.nutrition.mvi.state.NutritionPartialState
import com.example.main.fragments.nutrition.mvi.state.NutritionState
import javax.inject.Inject

class NutritionReducer @Inject constructor() : MviReducer<NutritionPartialState, NutritionState> {
    override fun reduce(
        prevState: NutritionState,
        partialState: NutritionPartialState
    ): NutritionState {
        return when (partialState) {
            is NutritionPartialState.DateLoaded -> prevState.copy(dateUi = partialState.date)
            is NutritionPartialState.NutritionWithMealsLoaded -> prevState.copy(
                nutritionWithMealsUi = partialState.nutritionWithMealsUi
            )
        }
    }
}