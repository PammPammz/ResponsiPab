package com.example.responsipab.data.equipment

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

data class Category(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String
)

data class Equipment(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("image_url")
    val imageUrl: String,

    @SerializedName("price")
    val price: Double,

    @SerializedName("category")
    val category: Category
)

data class PaginatedEquipments(
    @SerializedName("current_page")
    val currentPage: Int,

    @SerializedName("data")
    val equipmentList: List<Equipment>,

    @SerializedName("last_page")
    val lastPage: Int,

    @SerializedName("total")
    val total: Int
)

data class EquipmentsResponse(
    @SerializedName("equipments")
    val paginatedEquipments: PaginatedEquipments,

    @SerializedName("categories")
    val categories: List<Category>
)

interface EquipmentApi {
    @GET("equipments")
    suspend fun getEquipments(): EquipmentsResponse
}