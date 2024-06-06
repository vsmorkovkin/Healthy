package com.example.activity.entity

import com.example.nutrition.entity.NutritionEntity

data class ActivityWithNutritionEntity(
    val nutrition: NutritionEntity = NutritionEntity(),
    val activityEntity: ActivityEntity = ActivityEntity()
)