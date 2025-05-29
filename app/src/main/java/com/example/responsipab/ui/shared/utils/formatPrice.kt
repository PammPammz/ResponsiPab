package com.example.responsipab.ui.shared.utils

fun formatPrice(price: Int): String {
    return if (price >= 1000) {
        val formattedPrice = price / 1000
        "${formattedPrice}K"
    } else {
        price.toString()
    }
}