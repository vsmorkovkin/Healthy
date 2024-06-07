package com.example.main.views

import com.example.main.R
import com.example.main.databinding.CardMealBinding
import com.example.main.fragments.nutrition.model.MealUi

fun CardMealBinding.initialize() {
    root.context.run {
        cardMealProteins.setTitle(getString(R.string.card_meal_proteins_title))
        cardMealFats.setTitle(getString(R.string.card_meal_fats_title))
        cardMealCarbs.setTitle(getString(R.string.card_meal_carbs_title))
    }
}

fun CardMealBinding.setMeal(mealUi: MealUi) {
    textViewMealTitle.text = mealUi.title
    textViewMealCaloriesValue.text = mealUi.nutritionUi.calories.toString()
    cardMealProteins.setValue(mealUi.nutritionUi.proteins)
    cardMealFats.setValue(mealUi.nutritionUi.fats)
    cardMealCarbs.setValue(mealUi.nutritionUi.carbs)
}