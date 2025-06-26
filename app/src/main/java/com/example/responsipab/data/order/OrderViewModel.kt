package com.example.responsipab.data.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val repository: OrderRepository
) : ViewModel() {

    private val _state = MutableStateFlow<OrderListState>(OrderListState.Loading)
    val state = _state.asStateFlow()

    init {
        loadOrders()
    }

    fun loadOrders() {
        viewModelScope.launch {
            _state.value = OrderListState.Loading
            repository.getOrders()
                .onSuccess { orders ->
                    _state.value = OrderListState.Success(orders)
                }
                .onFailure {
                    _state.value = OrderListState.Error(it.localizedMessage ?: "Failed to load orders")
                }
        }
    }
}