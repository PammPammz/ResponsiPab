// File: data/repository/CartRepository.kt
package com.example.responsipab.data

import com.example.responsipab.data.model.Camera
import com.example.responsipab.data.model.CartItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class CartRepository {

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: Flow<List<CartItem>> = _cartItems.asStateFlow()

    fun addToCart(
        camera: Camera,
        quantity: Int = 1,
        rentalDays: Int = 1,
        startDate: String = "",
        endDate: String = ""
    ): Result<Unit> {
        return try {
            val currentItems = _cartItems.value.toMutableList()

            // Cek apakah item sudah ada di cart
            val existingItemIndex = currentItems.indexOfFirst { it.camera.id == camera.id }

            if (existingItemIndex != -1) {
                // Update quantity jika item sudah ada
                val existingItem = currentItems[existingItemIndex]
                currentItems[existingItemIndex] = existingItem.copy(
                    quantity = existingItem.quantity + quantity
                )
            } else {
                // Tambah item baru
                val newCartItem = CartItem(
                    id = UUID.randomUUID().toString(),
                    camera = camera,
                    quantity = quantity,
                    rentalDays = rentalDays,
                    startDate = startDate,
                    endDate = endDate
                )
                currentItems.add(newCartItem)
            }

            _cartItems.value = currentItems
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun removeFromCart(cartItemId: String): Result<Unit> {
        return try {
            val currentItems = _cartItems.value.toMutableList()
            currentItems.removeAll { it.id == cartItemId }
            _cartItems.value = currentItems
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun updateQuantity(cartItemId: String, newQuantity: Int): Result<Unit> {
        return try {
            if (newQuantity <= 0) {
                return removeFromCart(cartItemId)
            }

            val currentItems = _cartItems.value.toMutableList()
            val itemIndex = currentItems.indexOfFirst { it.id == cartItemId }

            if (itemIndex != -1) {
                currentItems[itemIndex] = currentItems[itemIndex].copy(quantity = newQuantity)
                _cartItems.value = currentItems
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun clearCart(): Result<Unit> {
        return try {
            _cartItems.value = emptyList()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun getCartItemCount(): Int {
        return _cartItems.value.sumOf { it.quantity }
    }

    fun getTotalPrice(): Double {
        return _cartItems.value.sumOf { it.totalPrice }
    }
}