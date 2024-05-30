package com.example.nutrition.entity

data class MealEntity(
    val title: String,
    val calories: Int,
    val nutritionEntity: NutritionEntity
)