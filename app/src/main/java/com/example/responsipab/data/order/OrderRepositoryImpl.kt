package com.example.responsipab.data.order

import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val api: OrderApi
) : OrderRepository {
    override suspend fun placeOrder(request: CheckoutRequest): Result<Unit> {
        return try {
            val response = api.placeOrder(request)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to place order. Code: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}