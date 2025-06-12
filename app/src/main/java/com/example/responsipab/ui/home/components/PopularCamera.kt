// File: ui/home/components/PopularCameraSection.kt (Update)
package com.example.responsipab.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.responsipab.data.CameraRepository
import com.example.responsipab.data.model.Camera
import com.example.responsipab.ui.shared.utils.formatPrice
import com.example.responsipab.ui.viewmodel.CartViewModel

@Composable
fun PopularCameraSection(
    onCameraClick: (Camera) -> Unit,
    cartViewModel: CartViewModel,
    modifier: Modifier = Modifier
) {
    val popularCameras = CameraRepository.cameraList.take(5) // Ambil 5 kamera populer
    val cartState by cartViewModel.uiState.collectAsState()

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 4.dp)
    ) {
        items(popularCameras) { camera ->
            PopularCameraCard(
                camera = camera,
                onCameraClick = onCameraClick,
                onAddToCart = { cam, quantity, rentalDays ->
                    cartViewModel.addToCart(cam, quantity, rentalDays)
                },
                isLoading = cartState.isLoading
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PopularCameraCard(
    camera: Camera,
    onCameraClick: (Camera) -> Unit,
    onAddToCart: (Camera, Int, Int) -> Unit,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.width(200.dp),
        onClick = { onCameraClick(camera) },
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            // Camera Image
            Image(
                painter = painterResource(id = camera.imageRes),
                contentDescription = camera.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Camera Name
            Text(
                text = camera.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            // Brand
            Text(
                text = camera.brand,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Rating
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = Color(0xFFFFC107),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = camera.rating.toString(),
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Price
            Text(
                text = "Rp ${formatPrice(camera.price.toInt().toDouble())}/hari",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Add to Cart Button
            AddToCartButton(
                camera = camera,
                onAddToCart = onAddToCart,
                modifier = Modifier.fillMaxWidth(),
                isLoading = isLoading
            )
        }
    }
}