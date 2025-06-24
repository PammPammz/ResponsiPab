package com.example.responsipab.data.cart
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository
) : ViewModel() {

    private val _state = MutableStateFlow<CartState>(CartState.Loading)
    val state = _state.asStateFlow()

    init {
        loadCart()
    }

    fun loadCart() {
        viewModelScope.launch {
            _state.value = CartState.Loading
            repository.getCart()
                .onSuccess { items ->
                    _state.value = CartState.Success(items)
                }
                .onFailure {
                    _state.value = CartState.Error(it.localizedMessage ?: "Failed to load cart")
                }
        }
    }

    fun addToCart(equipmentId: Int) {
        viewModelScope.launch {
            Log.d("CartViewModel", "Attempting to add equipment ID: $equipmentId to cart...")
            repository.addToCart(equipmentId)
                .onSuccess {
                    Log.d("CartViewModel", "SUCCESS: Item added to cart!")
                }
                .onFailure { exception ->
                    Log.e("CartViewModel", "FAILURE: Could not add item to cart.", exception)
                }
        }
    }

    fun removeItem(cartItemId: Int) {
        viewModelScope.launch {
            repository.deleteCartItem(cartItemId)
                .onSuccess {
                    // Refresh the cart
                    loadCart()
                }
                .onFailure {
                    // Handle error
                }
        }
    }
}