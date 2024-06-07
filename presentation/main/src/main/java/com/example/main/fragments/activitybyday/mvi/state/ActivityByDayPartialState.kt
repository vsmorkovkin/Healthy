package com.example.main.fragments.activitybyday.mvi.state

import com.example.activity.entity.ActivityWithNutritionEntity
import com.example.common.mvi.MviPartialState

sealed interface ActivityByDayPartialState : MviPartialState {
    data class DateSelected(val selectedDateEntity: String) : ActivityByDayPartialState
    data class ActivityWithNutritionByDateLoaded(val activityWithNutritionEntity: ActivityWithNutritionEntity) : ActivityByDayPartialState
}