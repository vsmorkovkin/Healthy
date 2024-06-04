package com.example.nutrition.repository

import com.example.nutrition.entity.MealEntity
import com.example.nutrition.entity.NutritionEntity
import com.example.nutrition.entity.NutritionWithMealsEntity

interface NutritionRepository {
    suspend fun addMealByDate(mealEntity: MealEntity, date: String)
    fun deleteMealByDate(date: String)
    fun getNutritionByDate(date: String): NutritionEntity
    fun getNutritionWithMeals(): NutritionWithMealsEntity
}