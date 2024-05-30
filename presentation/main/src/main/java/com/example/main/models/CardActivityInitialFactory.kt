package com.example.main.models

import android.content.Context
import androidx.core.content.res.ResourcesCompat

object CardActivityInitialFactory {

    fun create(
        context: Context,
        titleId: Int,
        initialValueId: Int,
        valueMeasurementId: Int,
        targetProgressId: Int? = null,
        iconId: Int,
        colorId: Int,
        isPercentageVisible: Boolean
    ): CardActivityInitial {
        return context.run {
            CardActivityInitial(
                getString(titleId),
                getString(initialValueId),
                getString(valueMeasurementId),
                targetProgressId?.let { resources.getInteger(it) },
                ResourcesCompat.getDrawable(resources, iconId, theme)!!,
                resources.getColor(colorId, theme),
                isPercentageVisible
            )
        }
    }

}