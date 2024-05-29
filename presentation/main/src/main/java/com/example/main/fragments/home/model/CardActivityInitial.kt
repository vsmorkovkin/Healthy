package com.example.main.fragments.home.model

import android.graphics.drawable.Drawable

data class CardActivityInitial(
    val title: String,
    val initialValue: String,
    val valueMeasurement: String,
    val maxProgress: Int,
    val icon: Drawable,
    val itemColor: Int,
    val isPercentageVisible: Boolean
)