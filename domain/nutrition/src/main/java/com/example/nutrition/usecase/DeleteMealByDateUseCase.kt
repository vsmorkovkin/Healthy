package com.example.nutrition.usecase

import com.example.nutrition.repository.NutritionRepository

class DeleteMealByDateUseCase(
    private val nutritionRepository: NutritionRepository
) {
    suspend operator fun invoke(date: String, mealDateTimeOfCreation: Long) {
        nutritionRepository.deleteMealByDate(date, mealDateTimeOfCreation)
    }
}