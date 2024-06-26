package com.example.main.fragments.activitybyday.mvi.state

import com.example.activity.entity.ActivityWithNutritionEntity
import com.example.common.mvi.MviState

data class ActivityByDayState(
    val selectedDateEntity: String?,
    val activityWithNutritionEntity: ActivityWithNutritionEntity
) : MviState