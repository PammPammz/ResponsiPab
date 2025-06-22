package com.example.responsipab.data.auth

interface AuthRepository {
    suspend fun register(request: RegisterRequest): Result<RegisterResponse>
    suspend fun login(request: LoginRequest): Result<LoginResponse>
    suspend fun getCurrentUser(): Result<UserResponse>
    suspend fun logout()
}