package com.example.responsipab.data.order

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Body
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

interface OrderApi {
    @POST("orders")
    suspend fun placeOrder(@Body request: CheckoutRequest): Response<Unit>
}