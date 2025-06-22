package com.example.responsipab.data.auth

import android.util.Log
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val tokenManager: TokenManager
) : AuthRepository {

    override suspend fun register(request: RegisterRequest): Result<RegisterResponse> {
        return try {
            Log.d("Register", "Sending request to server...")
            val response = api.register(request)
            Log.d("Register", "Response received: $response")
            Result.success(response)
        } catch (e: Exception) {
            Log.e("Register", "An exception occurred", e)
            Result.failure(e)
        }
    }

    override suspend fun login(request: LoginRequest): Result<LoginResponse> {
        return try {
            val response = api.login(request)
            tokenManager.saveToken(response.token)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    override suspend fun getCurrentUser(): Result<UserResponse> {
        return try {
            val user = api.getCurrentUser()
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logout() {
        tokenManager.clearToken()
    }
}