package com.example.responsipab.data.order

import com.example.responsipab.data.equipment.Equipment
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

data class RentalPeriodRequest(
    @SerializedName("from")
    val from: String, // e.g., "2025-07-01"

    @SerializedName("to")
    val to: String // e.g., "2025-07-05"
)

data class CheckoutRequest(
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("address")
    val address: String?,
    @SerializedName("notes")
    val notes: String?,
    @SerializedName("purpose")
    val purpose: String?,
    @SerializedName("delivery_method")
    val deliveryMethod: String, // "delivery" or "pickup"
    @SerializedName("rental_period")
    val rentalPeriod: RentalPeriodRequest
)

data class OrderItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("price")
    val price: Double,
    @SerializedName("equipment")
    val equipment: Equipment
)

data class Order(
    @SerializedName("id")
    val id: Int,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("status")
    val status: String, // e.g., "pending", "approved", "rejected"
    @SerializedName("total")
    val total: Double,
    @SerializedName("rental_start")
    val rentalStart: String, // ISO 8601 String
    @SerializedName("rental_end")
    val rentalEnd: String, // ISO 8601 String
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("items")
    val items: List<OrderItem>
)

data class PaginatedOrders(
    @SerializedName("current_page")
    val currentPage: Int,

    @SerializedName("data")
    val data: List<Order>
)

data class OrdersResponse(
    @SerializedName("orders")
    val orders: PaginatedOrders
)

interface OrderApi {
    @POST("orders")
    suspend fun placeOrder(@Body request: CheckoutRequest): Response<Unit>

    @GET("orders")
    suspend fun getOrders(): OrdersResponse
}
