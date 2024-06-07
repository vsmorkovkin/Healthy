package com.example.main.views

import com.example.main.databinding.CardMealNutrientBinding

fun CardMealNutrientBinding.setTitle(title: String) {
    textViewMealNutrientTitle.text = title
}

fun CardMealNutrientBinding.setValue(value: Int) {
    textViewMealNutrientValue.text = value.toString()
}