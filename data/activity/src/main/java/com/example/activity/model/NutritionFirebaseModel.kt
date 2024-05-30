package com.example.activity.model

import com.example.nutrition.entity.NutritionEntity

data class NutritionFirebaseModel(
    val calories: Int = 0,
    val proteins: Int = 0,
    val fats: Int = 0,
    val carbs: Int = 0
) {
    fun toDomain() = NutritionEntity(
        calories, proteins, fats, carbs
    )
}
