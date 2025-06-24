package com.example.responsipab.data.cart

interface CartRepository {
    suspend fun getCart(): Result<List<CartItem>>
    suspend fun addToCart(equipmentId: Int): Result<Unit>
    suspend fun deleteCartItem(cartItemId: Int): Result<Unit>
}