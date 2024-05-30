package com.example.nutrition.repository

import com.example.nutrition.entity.MealEntity
import com.example.nutrition.entity.NutritionEntity

interface NutritionRepository {
    fun addTodayMeal(mealEntity: MealEntity)
    fun getTodayMeals(): List<MealEntity>
    fun getTodayNutrition(): NutritionEntity
}