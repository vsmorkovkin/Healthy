package com.example.nutrition.usecase

import com.example.nutrition.entity.NutritionWithMealsEntity
import com.example.nutrition.repository.NutritionRepository

class GetNutritionWithMealsUseCase(
    private val nutritionRepository: NutritionRepository
) {
    operator fun invoke(): NutritionWithMealsEntity {
        return nutritionRepository.getNutritionWithMeals()
    }
}