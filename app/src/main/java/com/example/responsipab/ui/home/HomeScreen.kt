package com.example.responsipab.ui.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.responsipab.data.auth.AuthViewModel
import com.example.responsipab.data.equipment.Equipment
import com.example.responsipab.data.equipment.EquipmentListState
import com.example.responsipab.data.equipment.EquipmentViewModel
import com.example.responsipab.ui.home.components.CategorySection
import com.example.responsipab.ui.home.components.HomeBottomBar
import com.example.responsipab.ui.home.components.HomeTopBar
import com.example.responsipab.ui.home.components.PopularCameraSection
import com.example.responsipab.ui.home.components.PromoCard
import com.example.responsipab.ui.home.components.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    onCameraClick: (Equipment) -> Unit,
    onNavigateToCart: () -> Unit,
    authViewModel: AuthViewModel = hiltViewModel(),
    equipmentViewModel: EquipmentViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val authState by authViewModel.uiState.collectAsStateWithLifecycle()
    val equipmentState by equipmentViewModel.state.collectAsStateWithLifecycle()

    val allEquipments = if (equipmentState is EquipmentListState.Success) {
        (equipmentState as EquipmentListState.Success).equipments
    } else {
        emptyList()
    }

    val popularEquipments = allEquipments.take(5)
    val newEquipments = allEquipments.sortedByDescending { it.id }.take(5)

    Scaffold(
        topBar = {
            HomeTopBar(
                authUiState = authState,
                onCartClick = onNavigateToCart,
                onLoginClick = { navController.navigate("login") },
                onLogoutClick = { authViewModel.logout() }
            )
        },
        bottomBar = { HomeBottomBar(navController = navController) }
    ) { innerPadding ->
        when (val state = equipmentState) {
            is EquipmentListState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is EquipmentListState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Gagal memuat data: ${state.message}",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
            is EquipmentListState.Success -> {
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
                                "Peralatan Populer",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            TextButton(onClick = { /* TODO: Lihat semua kamera populer */ }) {
                                Text("Lihat Semua")
                            }
                        }
                        PopularCameraSection(
                            popularEquipments = popularEquipments,
                            onCameraClick = onCameraClick,
                        )
                    }

                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Peralatan Terbaru",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            TextButton(onClick = { /* TODO: Lihat semua kamera terbaru */ }) {
                                Text("Lihat Semua")
                            }
                        }
                        PopularCameraSection(
                            popularEquipments = newEquipments,
                            onCameraClick = { onCameraClick },
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}