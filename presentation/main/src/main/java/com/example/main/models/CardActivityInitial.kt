package com.example.main.models

import android.graphics.drawable.Drawable

data class CardActivityInitial(
    val title: String,
    val valueMeasurement: String,
    val maxProgress: Int? = null,
    val icon: Drawable,
    val itemColor: Int,
    val isPercentageVisible: Boolean
)