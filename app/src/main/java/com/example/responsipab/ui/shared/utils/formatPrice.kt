package com.example.responsipab.ui.shared.utils

fun formatPrice(price: Double): String {
    val priceInt = price.toInt()
    return if (priceInt >= 1000) {
        val formattedPrice = priceInt / 1000
        "Rp ${formattedPrice}K"
    } else {
        "Rp $priceInt"
    }
}
