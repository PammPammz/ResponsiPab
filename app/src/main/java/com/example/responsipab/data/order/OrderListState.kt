package com.example.responsipab.data.order

sealed interface OrderListState {
    object Loading : OrderListState
    data class Success(val orders: List<Order>) : OrderListState
    data class Error(val message: String) : OrderListState
}