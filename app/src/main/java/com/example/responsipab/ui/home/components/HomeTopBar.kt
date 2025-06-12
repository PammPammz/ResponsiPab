// File: ui/home/components/HomeTopBar.kt (Update)
package com.example.responsipab.ui.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.responsipab.ui.home.components.CartBadge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    cartItemCount: Int = 0,
    onCartClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Column {
                Text(
                    text = "Selamat Datang! 👋",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Rental Kamera",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        actions = {
            // Cart Badge
            CartBadge(
                itemCount = cartItemCount,
                onClick = onCartClick
            )

            // Notification Icon
            IconButton(onClick = { /* TODO: Handle notification */ }) {
                Icon(
                    Icons.Default.Notifications,
                    contentDescription = "Notifikasi"
                )
            }
        },
        modifier = modifier
    )
}