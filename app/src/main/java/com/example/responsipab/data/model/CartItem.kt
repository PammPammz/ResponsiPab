// File: data/model/CartItem.kt
package com.example.responsipab.data.model

data class CartItem(
    val id: String = "",
    val camera: Camera,
    val quantity: Int = 1,
    val rentalDays: Int = 1,
    val startDate: String = "",
    val endDate: String = ""
) {
    val totalPrice: Double
        get() = camera.price * quantity * rentalDays
}