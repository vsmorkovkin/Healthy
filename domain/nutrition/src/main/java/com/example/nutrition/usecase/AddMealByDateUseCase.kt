package com.example.nutrition.usecase

import com.example.nutrition.entity.MealEntity
import com.example.nutrition.repository.NutritionRepository

class AddMealByDateUseCase(
    private val nutritionRepository: NutritionRepository
) {
    suspend operator fun invoke(mealEntity: MealEntity, date: String) {
        nutritionRepository.addMealByDate(mealEntity, date)
    }
}