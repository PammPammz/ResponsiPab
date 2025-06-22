package com.example.responsipab.data.auth

sealed interface AuthUiState {
    object Loading : AuthUiState

    data class Authenticated(val user: User) : AuthUiState

    object Unauthenticated : AuthUiState
}