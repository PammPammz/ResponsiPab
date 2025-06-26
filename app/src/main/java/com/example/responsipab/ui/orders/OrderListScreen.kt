package com.example.responsipab.ui.orders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.responsipab.data.auth.AuthViewModel
import com.example.responsipab.data.order.OrderListState
import com.example.responsipab.data.order.OrderViewModel
import com.example.responsipab.ui.home.components.HomeBottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderListScreen(
    navController: NavHostController,
    viewModel: OrderViewModel = hiltViewModel(),
) {
    val orderState by viewModel.state.collectAsStateWithLifecycle()

    val orders = if (orderState is OrderListState.Success) {
        (orderState as OrderListState.Success).orders
    } else {
        emptyList()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Daftar Pesanan") })
        },
        bottomBar = { HomeBottomBar(navController = navController) }
    ) { paddingValues ->
        when (val state = orderState) {
            is OrderListState.Loading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
            }
            is OrderListState.Error -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text(text = state.message) }
            }
            is OrderListState.Success -> {
                if (orders.isEmpty()) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("You have no orders.") }
                } else {
                    LazyColumn(
                        contentPadding = paddingValues,
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(orders, key = { it.id }) { order ->
                            OrderCard(order = order )
                            Divider()
                        }
                    }
                }
            }
        }
    }
}
