package com.example.main.views

import com.example.main.R
import com.example.main.databinding.CardNutritionBinding
import com.example.main.fragments.activitybyday.model.NutritionUi

fun CardNutritionBinding.initialize() {
    root.context.run {
        cardProteins.initialize(
            getString(R.string.card_proteins_title),
            getColor(R.color.color_proteins_progress),
            resources.getInteger(R.integer.card_proteins_target_value)
        )

        cardFats.initialize(
            getString(R.string.card_fats_title),
            getColor(R.color.color_fats_progress),
            resources.getInteger(R.integer.card_fats_target_value)
        )

        cardCarbs.initialize(
            getString(R.string.card_carbs_title),
            getColor(R.color.color_carbs_progress),
            resources.getInteger(R.integer.card_carbs_target_value)
        )
    }

    initializeValues()
}

fun CardNutritionBinding.initializeValues() {
    cardCalories.setValue(0)
    cardProteins.setValue(0)
    cardFats.setValue(0)
    cardCarbs.setValue(0)
}

fun CardNutritionBinding.setValue(nutritionUi: NutritionUi) {
    cardCalories.setValue(nutritionUi.calories)
    cardProteins.setValue(nutritionUi.proteins)
    cardFats.setValue(nutritionUi.fats)
    cardCarbs.setValue(nutritionUi.carbs)
}