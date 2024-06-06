package com.example.main.fragments.home.mvi.store

import com.example.common.mvi.MviReducer
import com.example.main.fragments.home.mvi.state.HomePartialState
import com.example.main.fragments.home.mvi.state.HomeState
import com.example.nutrition.entity.NutritionEntity
import javax.inject.Inject
import kotlin.random.Random

class HomeReducer @Inject constructor() : MviReducer<HomePartialState, HomeState> {
    override fun reduce(prevState: HomeState, partialState: HomePartialState): HomeState {
        return when (partialState) {
            is HomePartialState.CurrentDateLoaded -> prevState.copy(currentDate = partialState.currentDate)
            is HomePartialState.ActivityLoaded -> prevState.copy(activityEntity = partialState.activityEntity)
            is HomePartialState.NutritionLoaded -> prevState.copy(nutritionEntity = partialState.nutritionEntity)
        }
    }
}