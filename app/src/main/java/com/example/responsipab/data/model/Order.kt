package com.example.responsipab.data.model

data class Order(
    val id: String,
    val status: String,
    val startDate: String,
    val endDate: String,
    val totalPrice: Double,
    val items: List<OrderItem>,
    val dateOrdered: String
)

data class OrderItem(
    val name: String,
    val quantity: Int
)

enum class OrderStatus {
    PENDING, APPROVED, REJECTED, CANCELLED, COMPLETED
}