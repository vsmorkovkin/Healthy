package com.example.main.models

import android.content.Context
import androidx.core.content.res.ResourcesCompat

object CardActivityInitialFactory {

    fun create(
        context: Context,
        titleId: Int,
        valueMeasurementId: Int,
        targetProgressId: Int? = null,
        iconId: Int,
        colorId: Int,
        isPercentageVisible: Boolean
    ): CardActivityInitial {
        return context.run {
            CardActivityInitial(
                title = getString(titleId),
                valueMeasurement = getString(valueMeasurementId),
                maxProgress = targetProgressId?.let { resources.getInteger(it) },
                icon = ResourcesCompat.getDrawable(resources, iconId, theme)!!,
                itemColor = resources.getColor(colorId, theme),
                isPercentageVisible = isPercentageVisible
            )
        }
    }

}