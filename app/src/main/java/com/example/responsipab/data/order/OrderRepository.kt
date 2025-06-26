package com.example.responsipab.data.order

interface OrderRepository {
    suspend fun placeOrder(request: CheckoutRequest): Result<Unit>
    suspend fun getOrders(): Result<List<Order>>
}