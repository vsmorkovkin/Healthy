package com.example.nutrition.repository

import com.example.nutrition.entity.MealEntity
import com.example.nutrition.entity.NutritionEntity
import com.example.nutrition.entity.NutritionWithMealsEntity

interface NutritionRepository {
    suspend fun addMealByDate(mealEntity: MealEntity, date: String)
    suspend fun deleteMealByDate(date: String, mealDateTimeOfCreation: Long)
    suspend fun getNutritionByDate(date: String): NutritionEntity
    suspend fun getNutritionWithMeals(date: String): NutritionWithMealsEntity
}