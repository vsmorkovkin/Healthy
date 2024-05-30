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
        return prevState.copy(activityUi = partialState.activityUi)
    }
}