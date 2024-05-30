package com.example.main.views

import android.content.Context
import com.example.main.R
import com.example.main.databinding.ContainerCardsActivityBinding
import com.example.main.models.CardActivityInitialFactory


fun ContainerCardsActivityBinding.initialize(context: Context) {

    cardSteps.initialize(
        CardActivityInitialFactory.create(
            context,
            R.string.card_steps_title,
            R.string.card_steps_initial_value,
            R.string.card_steps_value_measurement,
            R.integer.card_steps_target_value,
            R.drawable.ic_steps,
            R.color.color_steps,
            true
        )
    )

    cardWater.initialize(
        CardActivityInitialFactory.create(
            context,
            R.string.card_water_title,
            R.string.card_water_initial_value,
            R.string.card_water_value_measurement,
            R.integer.card_water_target_value,
            R.drawable.ic_water,
            R.color.color_water,
            true
        )
    )

    cardSleep.initialize(
        CardActivityInitialFactory.create(
            context,
            R.string.card_sleep_title,
            R.string.card_sleep_initial_value,
            R.string.card_sleep_value_measurement,
            R.integer.card_sleep_target_value,
            R.drawable.ic_sleep,
            R.color.color_sleep,
            false
        )
    )

    cardWeight.initialize(
        CardActivityInitialFactory.create(
            context,
            R.string.card_weight_title,
            R.string.card_weight_initial_value,
            R.string.card_weight_value_measurement,
            null,
            R.drawable.ic_weight,
            R.color.color_weight,
            false
        )
    )
}