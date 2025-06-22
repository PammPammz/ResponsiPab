package com.example.responsipab.ui.auth

import android.util.Log
import com.example.responsipab.data.api.AuthApi
import com.example.responsipab.data.api.LoginRequest
import com.example.responsipab.data.api.LoginResponse
import com.example.responsipab.data.api.RegisterResponse
import com.example.responsipab.data.api.RegisterRequest
import com.example.responsipab.data.remote.ApiClient

class AuthRepository {
    private val api: AuthApi = ApiClient.authApi

    suspend fun register(request: RegisterRequest): Result<RegisterResponse> {
        return try {
            Log.d("Register", "Sending request to server...")
            val response = api.register(request)
            Log.d("Register", "Response received: $response")
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(request: LoginRequest): Result<LoginResponse> {
        return try {
            val response = api.login(request)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}