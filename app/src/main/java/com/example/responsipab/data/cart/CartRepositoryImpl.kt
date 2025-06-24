package com.example.responsipab.data.cart

import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val api: CartApi
) : CartRepository {

    override suspend fun getCart(): Result<List<CartItem>> {
        return try {
            Result.success(api.getCart())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addToCart(equipmentId: Int): Result<Unit> {
        return try {
            val response = api.addToCart(AddToCartRequest(equipmentId))
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to add item to cart. Code: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteCartItem(cartItemId: Int): Result<Unit> {
        return try {
            val response = api.deleteCartItem(cartItemId)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to delete item. Code: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}