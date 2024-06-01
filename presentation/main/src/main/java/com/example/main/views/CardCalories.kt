package com.example.main.views

import com.example.main.databinding.CardCaloriesBinding

fun CardCaloriesBinding.setValue(currentValue: Int) {
    valueCalories.text = currentValue.toString()
    progressCalories.progress = currentValue
}