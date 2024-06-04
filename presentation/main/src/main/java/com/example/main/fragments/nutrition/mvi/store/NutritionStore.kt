package com.example.main.fragments.nutrition.mvi.store

import com.example.common.mvi.MviStore
import com.example.main.fragments.nutrition.mvi.effect.NutritionEffect
import com.example.main.fragments.nutrition.mvi.intent.NutritionIntent
import com.example.main.fragments.nutrition.mvi.state.NutritionPartialState
import com.example.main.fragments.nutrition.mvi.state.NutritionState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NutritionStore @Inject constructor(
    actor: NutritionActor,
    reducer: NutritionReducer
) :
    MviStore<NutritionPartialState, NutritionIntent, NutritionState, NutritionEffect>(reducer, actor) {
    override fun initialStateCreator(): NutritionState {
        return NutritionState("", null, emptyList())
    }
}