package com.example.responsipab.data.model

data class Camera(
    val id: Int,
    val name: String,
    val price: Double,
    val rating: Float,
    val imageUrl: String,
    val brand: String,
    val imageRes: Int,
    val isAvailable: Boolean = true
)