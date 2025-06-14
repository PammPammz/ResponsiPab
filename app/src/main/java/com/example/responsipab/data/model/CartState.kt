// File: data/model/CartState.kt
package com.example.responsipab.data.model

data class CartState(
    val items: List<CartItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) {
    val totalItems: Int
        get() = items.sumOf { it.quantity }

    val totalPrice: Double
        get() = items.sumOf { it.totalPrice }
}