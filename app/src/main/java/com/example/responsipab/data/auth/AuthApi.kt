package com.example.responsipab.data.auth

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

data class User(
    val id: Int,
    val name: String,
    val email: String
)

data class UserResponse(
    val user: User
)

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val password_confirmation: String
)

data class RegisterResponse(
    val success: Boolean,
    val message: String,
    val data: User?
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val token: String,
    val user: User
)

@JvmSuppressWildcards
interface AuthApi {
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET("auth/me")
    suspend fun getCurrentUser(): UserResponse
}