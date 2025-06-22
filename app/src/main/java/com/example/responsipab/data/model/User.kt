package com.example.responsipab.data.model

data class UserResponse(
    val user: User,
    val token: String
)

data class User(
    val id: Int,
    val name: String,
    val email: String
)