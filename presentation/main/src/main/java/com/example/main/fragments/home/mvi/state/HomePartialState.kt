package com.example.main.fragments.home.mvi.state

import com.example.activity.entity.ActivityEntity
import com.example.common.mvi.MviPartialState
import com.example.nutrition.entity.NutritionEntity

sealed interface HomePartialState : MviPartialState {
    data class CurrentDateLoaded(val currentDate: String) : HomePartialState
    data class ActivityLoaded(val activityEntity: ActivityEntity) : HomePartialState
    data class NutritionLoaded(val nutritionEntity: NutritionEntity) : HomePartialState
}