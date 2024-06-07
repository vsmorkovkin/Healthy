package com.example.main.fragments.activitybyday.mvi.store

import com.example.common.mvi.MviReducer
import com.example.main.fragments.activitybyday.mvi.state.ActivityByDayPartialState
import com.example.main.fragments.activitybyday.mvi.state.ActivityByDayState
import javax.inject.Inject

class ActivityByDayReducer @Inject constructor() :
    MviReducer<ActivityByDayPartialState, ActivityByDayState> {

    override fun reduce(
        prevState: ActivityByDayState,
        partialState: ActivityByDayPartialState
    ): ActivityByDayState {
        return when (partialState) {
            is ActivityByDayPartialState.DateSelected -> {
                prevState.copy(selectedDateEntity = partialState.selectedDateEntity)
            }

            is ActivityByDayPartialState.ActivityWithNutritionByDateLoaded -> {
                prevState.copy(activityWithNutritionEntity = partialState.activityWithNutritionEntity)
            }
        }
    }
}