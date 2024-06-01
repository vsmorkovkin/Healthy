package com.example.main.views

import com.example.main.databinding.CardNutrientBinding


fun CardNutrientBinding.initialize(
    title: String,
    progressColor: Int
) {
    titleNutrient.text = title
    progressNutrient.setIndicatorColor(progressColor)
}

fun CardNutrientBinding.setValue(value: Int) {
    valueNutrient.text = value.toString()
    progressNutrient.progress = value
    percentageNutrient.text = value.toString()
}