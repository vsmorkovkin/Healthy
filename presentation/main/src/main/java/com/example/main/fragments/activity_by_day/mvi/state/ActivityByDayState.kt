package com.example.main.fragments.activity_by_day.mvi.state

import com.example.common.mvi.MviState
import com.example.main.fragments.activity_by_day.model.ActivityUi

data class ActivityByDayState(
    val selectedDateUi: String?,
    val selectedDateEntity: String?,
    val activityUi: ActivityUi?
) : MviState