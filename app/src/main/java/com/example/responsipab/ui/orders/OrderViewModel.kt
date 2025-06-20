package com.example.responsipab.ui.orders

import androidx.lifecycle.ViewModel
import com.example.responsipab.data.model.Order
import com.example.responsipab.data.repository.OrderRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class OrderViewModel(
    private val repository: OrderRepository = OrderRepository()
) : ViewModel() {

    // Public flow to observe orders
    val orders: StateFlow<List<Order>> = repository.orders
        .stateIn(
            scope = CoroutineScope(SupervisorJob() + Dispatchers.IO),
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addOrder(order: Order) {
        repository.addOrder(order)
    }
}
