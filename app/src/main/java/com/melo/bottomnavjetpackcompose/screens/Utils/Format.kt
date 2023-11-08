package com.melo.bottomnavjetpackcompose.screens.Utils

class Format {
    fun format2(number: String): String {
        val floatValue = number.toFloatOrNull() ?: 0.0f
        return String.format("%.2f", floatValue)
    }
}
