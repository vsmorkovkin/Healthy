package com.example.main.fragments.activity_by_day.mvi.store

import com.example.common.mvi.MviReducer
import com.example.main.fragments.activity_by_day.mvi.state.ActivityByDayPartialState
import com.example.main.fragments.activity_by_day.mvi.state.ActivityByDayState
import javax.inject.Inject

class ActivityByDayReducer @Inject constructor() :
    MviReducer<ActivityByDayPartialState, ActivityByDayState> {

    override fun reduce(
        prevState: ActivityByDayState,
        partialState: ActivityByDayPartialState
    ): ActivityByDayState {
        return when (partialState) {
            is ActivityByDayPartialState.ActivityByDaySelected -> {
                prevState.copy(
                    selectedDateUi = partialState.selectedDateUi,
                    selectedDateEntity = partialState.selectedDateEntity
                )
            }

            is ActivityByDayPartialState.ActivityByDayLoaded -> {
                prevState.copy(activityUi = partialState.activityUi)
            }
        }
    }
}