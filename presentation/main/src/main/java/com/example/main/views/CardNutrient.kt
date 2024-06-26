package com.example.main.views

import com.example.main.databinding.CardNutrientBinding

fun CardNutrientBinding.initialize(
    title: String,
    progressColor: Int,
    targetValue: Int
) {
    titleNutrient.text = title
    progressNutrient.setIndicatorColor(progressColor)
    progressNutrient.max = targetValue
}

fun CardNutrientBinding.setValue(value: Int) {
    valueNutrient.text = value.toString()
    progressNutrient.progress = value
    percentageNutrient.text = minOf((value.toFloat() / progressNutrient.max * 100).toInt(), 100).toString()
}