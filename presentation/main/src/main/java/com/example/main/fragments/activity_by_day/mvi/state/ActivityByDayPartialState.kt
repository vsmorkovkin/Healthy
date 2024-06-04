package com.example.main.fragments.activity_by_day.mvi.state

import com.example.common.mvi.MviPartialState
import com.example.main.fragments.activity_by_day.model.ActivityUi

sealed interface ActivityByDayPartialState : MviPartialState {
    data class ActivityByDaySelected(val selectedDateUi: String, val selectedDateEntity: String) : ActivityByDayPartialState
    data class ActivityByDayLoaded(val activityUi: ActivityUi?) : ActivityByDayPartialState
}