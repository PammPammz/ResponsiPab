package com.example.responsipab.ui.camera_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.responsipab.data.cart.CartViewModel
import com.example.responsipab.data.equipment.EquipmentDetailState
import com.example.responsipab.data.equipment.EquipmentDetailViewModel
import com.example.responsipab.ui.home.components.CartBadge
import com.example.responsipab.ui.shared.utils.formatPrice


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraDetailScreen(
    onBack: () -> Unit,
    onNavigateToCart: () -> Unit,
    viewModel: EquipmentDetailViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (val detailState = state) {
        is EquipmentDetailState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is EquipmentDetailState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error: ${detailState.message}")
            }
        }
        is EquipmentDetailState.Success -> {
            val equipment = detailState.equipment

            val isInCart = detailState.isInCart

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(equipment.name) },
                        navigationIcon = {
                            IconButton(onClick = onBack) {
                                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Kembali")
                            }
                        },
                        actions = {
                            CartBadge(
                                itemCount = 0,
                                onClick = onNavigateToCart
                            )
                            IconButton(onClick = { /* TODO */ }) {
                                Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "Favorit")
                            }
                            IconButton(onClick = { /* TODO */ }) {
                                Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
                            }
                        }
                    )
                }
            ) { innerPadding ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    item {
                        AsyncImage(
                            model = equipment.imageUrl,
                            contentDescription = equipment.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                        )
                    }

                    item {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = equipment.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "${formatPrice(equipment.price)}/Hari",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))

                            Text(text = "Deskripsi", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = equipment.description,
                                fontSize = 14.sp,
                                lineHeight = 20.sp
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                            Button(
                                onClick = {
                                    if (!isInCart) {
                                         cartViewModel.addToCart(equipment.id)
                                    }
                                },
                                modifier = Modifier.fillMaxWidth(),
                                enabled = !isInCart
                            ) {
                                Text(
                                    text = if (isInCart) "Already in Cart" else "Add to Cart",
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}