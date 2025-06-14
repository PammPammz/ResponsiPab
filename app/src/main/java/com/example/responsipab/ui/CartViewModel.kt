// File: ui/viewmodel/CartViewModel.kt
package com.example.responsipab.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.responsipab.data.CartRepository
import com.example.responsipab.data.model.CartState
import com.example.responsipab.data.model.Camera
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val cartRepository: CartRepository = CartRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(CartState())
    val uiState: StateFlow<CartState> = _uiState.asStateFlow()

    private val _showSuccessMessage = MutableStateFlow(false)
    val showSuccessMessage: StateFlow<Boolean> = _showSuccessMessage.asStateFlow()

    init {
        observeCartItems()
    }

    private fun observeCartItems() {
        viewModelScope.launch {
            try {
                cartRepository.cartItems.collect { items ->
                    _uiState.value = _uiState.value.copy(
                        items = items,
                        isLoading = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Error loading cart: ${e.message}"
                )
            }
        }
    }

    fun addToCart(
        camera: Camera,
        quantity: Int = 1,
        rentalDays: Int = 1,
        startDate: String = "",
        endDate: String = ""
    ) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true, error = null)

                cartRepository.addToCart(camera, quantity, rentalDays, startDate, endDate)
                    .onSuccess {
                        _showSuccessMessage.value = true
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = null
                        )
                    }
                    .onFailure { exception ->
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = "Gagal menambahkan ke keranjang: ${exception.message}"
                        )
                    }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Error: ${e.message}"
                )
            }
        }
    }

    fun removeFromCart(cartItemId: String) {
        viewModelScope.launch {
            try {
                cartRepository.removeFromCart(cartItemId)
                    .onFailure { exception ->
                        _uiState.value = _uiState.value.copy(
                            error = "Gagal menghapus item: ${exception.message}"
                        )
                    }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Error: ${e.message}"
                )
            }
        }
    }

    fun updateQuantity(cartItemId: String, newQuantity: Int) {
        viewModelScope.launch {
            try {
                cartRepository.updateQuantity(cartItemId, newQuantity)
                    .onFailure { exception ->
                        _uiState.value = _uiState.value.copy(
                            error = "Gagal update quantity: ${exception.message}"
                        )
                    }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Error: ${e.message}"
                )
            }
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            try {
                cartRepository.clearCart()
                    .onFailure { exception ->
                        _uiState.value = _uiState.value.copy(
                            error = "Gagal menghapus keranjang: ${exception.message}"
                        )
                    }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Error: ${e.message}"
                )
            }
        }
    }

    fun dismissSuccessMessage() {
        _showSuccessMessage.value = false
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}