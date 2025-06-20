package com.example.responsipab.data.repository

import com.example.responsipab.data.model.Order
import com.example.responsipab.data.model.OrderItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OrderRepository {

    private val _orders = MutableStateFlow<List<Order>>(
        listOf(
            Order(
                id = "ORD-001",
                status = "pending",
                startDate = "2025-06-15",
                endDate = "2025-06-17",
                totalPrice = 500000.0,
                items = listOf(
                    OrderItem("Canon EOS R6", 1),
                    OrderItem("Sony A7 III", 1)
                ),
                dateOrdered = "2025-06-14"
            ),
            Order(
                id = "ORD-002",
                status = "approved",
                startDate = "2025-06-10",
                endDate = "2025-06-12",
                totalPrice = 750000.0,
                items = listOf(
                    OrderItem("Nikon Z6", 2)
                ),
                dateOrdered = "2025-06-09"
            ),
            Order(
                id = "ORD-003",
                status = "completed",
                startDate = "2025-05-20",
                endDate = "2025-05-22",
                totalPrice = 300000.0,
                items = listOf(
                    OrderItem("Fujifilm X-T30", 1),
                    OrderItem("Tripod", 1)
                ),
                dateOrdered = "2025-05-19"
            )
        )
    )

    val orders: Flow<List<Order>> = _orders.asStateFlow()

    fun addOrder(order: Order) {
        val current = _orders.value.toMutableList()
        current.add(0, order)
        _orders.value = current
    }
}
