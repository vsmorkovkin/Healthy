package com.example.main.fragments.home.mvi.state

import com.example.activity.entity.ActivityEntity
import com.example.common.mvi.MviState
import com.example.nutrition.entity.NutritionEntity

data class HomeState(
    val currentDate: String,
    val nutritionEntity: NutritionEntity,
    val activityEntity: ActivityEntity
) : MviState
