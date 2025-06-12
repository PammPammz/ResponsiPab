package com.example.responsipab.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.responsipab.data.model.Camera
import com.example.responsipab.ui.home.components.CategorySection
import com.example.responsipab.ui.home.components.HomeBottomBar
import com.example.responsipab.ui.home.components.HomeTopBar
import com.example.responsipab.ui.home.components.NewCameraSection
import com.example.responsipab.ui.home.components.PopularCameraSection
import com.example.responsipab.ui.home.components.PromoCard
import com.example.responsipab.ui.home.components.SearchBar
import com.example.responsipab.ui.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    cartViewModel: CartViewModel,
    onCameraClick: (Camera) -> Unit,
    onNavigateToCart: () -> Unit,
    modifier: Modifier = Modifier
) {
    val cartState by cartViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            HomeTopBar(
                cartItemCount = cartState.totalItems,
                onCartClick = onNavigateToCart
            )
        },
        bottomBar = { HomeBottomBar() }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                SearchBar()
            }

            item {
                PromoCard()
            }

            item {
                Text(
                    "Kategori Kamera",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                CategorySection()
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Kamera Populer",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    TextButton(onClick = { /* TODO: Lihat semua kamera populer */ }) {
                        Text("Lihat Semua")
                    }
                }
                PopularCameraSection(
                    onCameraClick = onCameraClick,
                    cartViewModel = cartViewModel
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Kamera Terbaru",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    TextButton(onClick = { /* TODO: Lihat semua kamera terbaru */ }) {
                        Text("Lihat Semua")
                    }
                }
                NewCameraSection(
                    onCameraClick = onCameraClick,
                    cartViewModel = cartViewModel
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}