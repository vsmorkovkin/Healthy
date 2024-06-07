package com.example.main.utils

object DecimalFormatter {

    fun format(value: Float): String {
        return if (value == value.toInt().toFloat()) {
            value.toInt().toString()
        } else {
            String.format("%.1f", value).replace(',', '.')
        }
    }
}