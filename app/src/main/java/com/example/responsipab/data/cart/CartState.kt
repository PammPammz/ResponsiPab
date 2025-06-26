package com.example.responsipab.data.cart

sealed interface CartState {
    object Loading : CartState
    data class Success(val items: List<CartItem>) : CartState
    data class Error(val message: String) : CartState
}