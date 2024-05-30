package com.example.main.views

import android.graphics.drawable.LayerDrawable
import androidx.core.view.isVisible
import com.example.main.databinding.CardActivityBinding
import com.example.main.models.CardActivityInitial


fun CardActivityBinding.initialize(cardActivityInitial: CardActivityInitial) {
    // Title setup
    title.text = cardActivityInitial.title

    // Icon setup
    icon.setImageDrawable(cardActivityInitial.icon.apply { setTint(cardActivityInitial.itemColor) })

    // Value setup
    value.text = cardActivityInitial.initialValue
    valueMeasurement.text = cardActivityInitial.valueMeasurement

    // Percentage setup
    cardActivityInitial.isPercentageVisible.let {
        percentage.isVisible = it
        percentSign.isVisible = it
    }

    // Progress bar setup
    val layerDrawable = progress.progressDrawable as LayerDrawable
    layerDrawable.findDrawableByLayerId(android.R.id.progress)
        .setTint(cardActivityInitial.itemColor)
    if (cardActivityInitial.maxProgress != null) {
        progress.max = cardActivityInitial.maxProgress
    } else {
        progress.isVisible = false
    }
}


fun CardActivityBinding.setValue(currentValue: Int) {
    value.text = currentValue.toString()
    setProgress(currentValue)
}

private const val MAX_PERCENT = 100

private fun CardActivityBinding.setProgress(currentProgress: Int) {
    progress.progress = currentProgress
    percentage.text = minOf(((progress.progress.toFloat() / progress.max) * 100).toInt(), MAX_PERCENT).toString()
}