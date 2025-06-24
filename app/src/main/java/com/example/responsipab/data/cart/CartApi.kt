package com.example.responsipab.data.cart

import com.example.responsipab.data.equipment.Equipment
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

data class CartItem(
    @SerializedName("id")
    val id: Int,

    @SerializedName("quantity")
    val quantity: Int,

    @SerializedName("equipment")
    val equipment: Equipment
)

data class AddToCartRequest(
    @SerializedName("equipment_id")
    val equipmentId: Int
)

data class CartResponse(
    @SerializedName("cartItems")
    val cartItems: List<CartItem>
)

interface CartApi {
    @GET("cart")
    suspend fun getCart(): CartResponse

    @POST("cart")
    suspend fun addToCart(@Body request: AddToCartRequest): Response<Unit>

    @DELETE("cart/{id}")
    suspend fun deleteCartItem(@Path("id") cartItemId: Int): Response<Unit>
}