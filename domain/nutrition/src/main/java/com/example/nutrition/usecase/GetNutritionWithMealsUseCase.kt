package com.example.nutrition.usecase

import com.example.nutrition.entity.NutritionWithMealsEntity
import com.example.nutrition.repository.NutritionRepository

class GetNutritionWithMealsUseCase(
    private val nutritionRepository: NutritionRepository
) {
    suspend operator fun invoke(date: String): NutritionWithMealsEntity {
        return nutritionRepository.getNutritionWithMeals(date)
    }
}