package com.example.nutrition.usecase

import com.example.nutrition.entity.NutritionEntity
import com.example.nutrition.repository.NutritionRepository

class GetNutritionByDateUseCase(
    private val nutritionRepository: NutritionRepository
) {
    suspend operator fun invoke(date: String): NutritionEntity {
        return nutritionRepository.getNutritionByDate(date)
    }
}