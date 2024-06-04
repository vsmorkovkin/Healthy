package com.example.nutrition.entity

data class NutritionWithMealsEntity(
    val totalNutrition: NutritionEntity = NutritionEntity(),
    val mealsList: List<MealEntity> = emptyList()
)