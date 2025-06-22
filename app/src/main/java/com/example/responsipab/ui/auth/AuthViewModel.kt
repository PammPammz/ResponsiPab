package com.example.responsipab.ui.auth

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.responsipab.data.api.LoginRequest
import com.example.responsipab.data.api.RegisterRequest
import com.example.responsipab.data.api.RegisterResponse
import com.example.responsipab.ui.auth.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository = AuthRepository()
) : ViewModel() {

    private val _registerSuccess = mutableStateOf(false)
    val registerSuccess = _registerSuccess

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error


    private val _loginSuccess = mutableStateOf(false)
    val loginSuccess = _loginSuccess

    private val _loginError = mutableStateOf<String?>(null)
    val loginError = _loginError

    fun register(name: String, email: String, password: String, confirmPassword: String) {
        viewModelScope.launch {
            val request = RegisterRequest(name, email, password, confirmPassword)
            val result = repository.register(request)

            result.onSuccess {
                _registerSuccess.value = true
            }.onFailure {
                _error.value = it.message ?: "Unknown error"
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = repository.login(LoginRequest(email, password))
            result.onSuccess {
                _loginSuccess.value = true
                _loginError.value = null
                // Save token locally if needed
                Log.d("Login", "Token: ${it.token}")
            }.onFailure {
                _loginError.value = it.localizedMessage ?: "Login failed"
            }
        }
    }

    fun resetLoginState() {
        _loginSuccess.value = false
        _loginError.value = null
    }

    fun clearMessages() {
        _registerSuccess.value = false
        _error.value = null
    }
}