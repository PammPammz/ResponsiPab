package com.example.responsipab.ui.camera_detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.responsipab.data.CameraRepository
import com.example.responsipab.ui.camera_detail.components.SpecificationRow
import com.example.responsipab.ui.home.components.AddToCartButton
import com.example.responsipab.ui.home.components.CartBadge
import com.example.responsipab.ui.shared.utils.formatPrice
import com.example.responsipab.ui.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraDetailScreen(
    cameraId: Int?,
    cartViewModel: CartViewModel,
    onBack: () -> Unit,
    onNavigateToCart: () -> Unit
) {
    val context = LocalContext.current
    val camera = CameraRepository.getCameraById(cameraId)
    val cartState by cartViewModel.uiState.collectAsState()

    if (camera == null) {
        Text("Camera not found")
        return
    }

    // Fungsi untuk membuka WhatsApp
    fun openWhatsApp(phoneNumber: String, message: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            val url = "https://api.whatsapp.com/send?phone=$phoneNumber&text=${Uri.encode(message)}"
            intent.data = Uri.parse(url)
            context.startActivity(intent)
        } catch (e: Exception) {
            // Jika WhatsApp tidak terinstall, buka di browser
            val intent = Intent(Intent.ACTION_VIEW)
            val url = "https://web.whatsapp.com/send?phone=$phoneNumber&text=${Uri.encode(message)}"
            intent.data = Uri.parse(url)
            context.startActivity(intent)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Kamera") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                },
                actions = {
                    CartBadge(
                        itemCount = cartState.totalItems,
                        onClick = onNavigateToCart
                    )
                    IconButton(onClick = { /* TODO: Implementasi favorit */ }) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Favorit"
                        )
                    }
                    IconButton(onClick = { /* TODO: Implementasi share */ }) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share"
                        )
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
                // Gambar kamera
                Image(
                    painter = painterResource(id = camera.imageRes),
                    contentDescription = camera.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
            }

            item {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    // Nama kamera
                    Text(
                        text = camera.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Rating dan harga
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Rating",
                                tint = Color(0xFFFFC107),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${camera.rating} (125 ulasan)",
                                fontSize = 16.sp
                            )
                        }

                        Text(
                            text = "Rp ${formatPrice(camera.price)}/Hari",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Spesifikasi
                    Text(
                        text = "Spesifikasi",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            SpecificationRow("Resolusi", "30.4 MP")
                            SpecificationRow("ISO", "100-32000")
                            SpecificationRow("Video", "4K UHD")
                            SpecificationRow("Baterai", "1865 shots")
                            SpecificationRow("Berat", "890g")
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Deskripsi
                    Text(
                        text = "Deskripsi",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Kamera DSLR profesional dengan kualitas gambar luar biasa. Dilengkapi dengan sensor full-frame dan teknologi autofocus yang canggih. Cocok untuk fotografi profesional, pernikahan, dan event penting lainnya. Sudah termasuk lensa kit 24-70mm f/2.8.",
                        fontSize = 14.sp,
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Add to Cart Button (NEW)
                    AddToCartButton(
                        camera = camera,
                        onAddToCart = { cam, quantity, rentalDays ->
                            cartViewModel.addToCart(cam, quantity, rentalDays)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        isLoading = cartState.isLoading
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Tombol booking via WhatsApp
                    Button(
                        onClick = {
                            val phoneNumber = "6281234567890" // Ganti dengan nomor WhatsApp Anda
                            val message = "Halo, saya tertarik untuk menyewa kamera ${camera.name} dengan harga Rp ${formatPrice(camera.price)}/hari. Apakah masih tersedia?"
                            openWhatsApp(phoneNumber, message)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Booking",
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Booking via WhatsApp",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedButton(
                        onClick = { /* TODO: Implementasi chat */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = "Chat",
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Hubungi Penyewa",
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}