package com.example.responsipab.ui.orders

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.responsipab.data.model.Order
import androidx.compose.foundation.lazy.items
import androidx.navigation.NavHostController
import com.example.responsipab.ui.home.components.HomeBottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderListScreen(
    navController: NavHostController,
    orders: List<Order>,
    onOrderClick: (Order) -> Unit
) {
    Scaffold (
        topBar = {
            TopAppBar(title = { Text("Daftar Pesanan") })
        },
        bottomBar = { HomeBottomBar(navController = navController) }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.fillMaxSize()
        ) {
            items(orders) { order ->
                OrderCard(order = order, onClick = { onOrderClick(order) })
                Divider()
            }
        }
    }
}