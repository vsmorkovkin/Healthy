package com.example.activity.usecase

import com.example.activity.entity.ActivityWithNutritionEntity
import com.example.nutrition.usecase.GetNutritionByDateUseCase

class GetActivityWithNutritionByDateUseCase(
    private val getActivityByDateUseCase: GetActivityByDateUseCase,
    private val getNutritionByDateUseCase: GetNutritionByDateUseCase
) {
    suspend operator fun invoke(date: String): ActivityWithNutritionEntity {
        val activity = getActivityByDateUseCase(date)
        val nutrition = getNutritionByDateUseCase(date)
        return ActivityWithNutritionEntity(nutrition, activity)
    }
}