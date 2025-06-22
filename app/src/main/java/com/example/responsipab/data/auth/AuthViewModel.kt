package com.example.responsipab.data.auth

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Loading)
    // The public, read-only state flow for the UI to observe.
    val uiState = _uiState.asStateFlow()

    init {
        checkUserLoggedIn()
        checkTokenForDebug()
    }

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
                Log.d("Login", "Login successful, token is saved.")
                checkUserLoggedIn()
            }.onFailure {
                _loginError.value = it.localizedMessage ?: "Login failed"
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
            _uiState.value = AuthUiState.Unauthenticated
        }
    }

    fun checkUserLoggedIn() {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            repository.getCurrentUser()
                .onSuccess { user ->
                    Log.d("AuthViewModel", "Check successful. User is Authenticated: $user")
                    _uiState.value = AuthUiState.Authenticated(user.user)
                }
                .onFailure { exception ->
                    Log.e("AuthViewModel", "checkUserLoggedIn FAILED. Exception:", exception)

                    _uiState.value = AuthUiState.Unauthenticated
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

    private fun checkTokenForDebug() {
        viewModelScope.launch {
            tokenManager.getToken().collect { token ->
                if (token != null) {
                    Log.d("TokenCheck", "Current token is: $token")
                } else {
                    Log.d("TokenCheck", "Current token is: null")
                }
            }
        }
    }
}