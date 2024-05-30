package com.example.main.fragments.activity_by_day.mvi.state

import com.example.common.mvi.MviPartialState
import com.example.main.fragments.activity_by_day.model.ActivityUi

data class ActivityByDayPartialState(val activityUi: ActivityUi?) : MviPartialState