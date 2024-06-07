package com.example.main.extensions

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.getText(): String {
    return editText!!.text.toString()
}

fun TextInputLayout.getInt(): Int {
    return editText!!.text.toString().toIntOrNull() ?: 0
}