package com.example.nutrition.entity

data class MealEntity(
    val datetimeOfCreation: Long = 0,
    val title: String = "",
    val nutritionEntity: NutritionEntity = NutritionEntity()
)