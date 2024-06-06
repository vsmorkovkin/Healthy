package com.example.main.views

import android.graphics.drawable.LayerDrawable
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.main.databinding.CardActivityBinding
import com.example.main.models.CardActivityInitial
import com.example.main.utils.DecimalFormatter


// Initializing
fun CardActivityBinding.initialize(cardActivityInitial: CardActivityInitial) {
    // Title setup
    title.text = cardActivityInitial.title

    // Icon setup
    icon.setImageDrawable(cardActivityInitial.icon.apply { setTint(cardActivityInitial.itemColor) })

    // Value setup
    //value.text = cardActivityInitial.initialValue
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
    progress.progressDrawable = layerDrawable
    if (cardActivityInitial.maxProgress != null) {
        progress.max = cardActivityInitial.maxProgress
    } else {
        progress.isVisible = false
    }
}

fun CardActivityBinding.initializeValue(initValue: String) {
    value.text = initValue
    percentage.text = "0"
    progress.progress = 0
}


// Setting values
fun CardActivityBinding.setValue(currentValue: Int) {
    value.text = currentValue.toString()
    setProgress(currentValue)
}

fun CardActivityBinding.setWaterIntake(waterIntakeMl: Int) {
    val waterIntakeLiters = waterIntakeMl.toFloat() / 1000
    value.text = if (waterIntakeMl == 0) "0" else DecimalFormatter.format(waterIntakeLiters)
    setProgress(waterIntakeMl)
}

fun CardActivityBinding.setSleepTime(sleepTime: Float) {
    value.text = if (sleepTime == 0f) "--" else DecimalFormatter.format(sleepTime)
    setProgress(sleepTime.toInt())
}

fun CardActivityBinding.setCurrentWeight(currentWeight: Float) {
    value.text = if (currentWeight == 0f) "--" else DecimalFormatter.format(currentWeight)
}


// Setting progress
private const val MAX_PERCENT = 100

private fun CardActivityBinding.setProgress(currentProgress: Int) {
    progress.progress = currentProgress
    //Log.d("Progress", "max=${progress.max}")
    percentage.text = minOf(
        ((currentProgress.toFloat() / progress.max) * 100).toInt(),
        MAX_PERCENT
    ).toString()
}