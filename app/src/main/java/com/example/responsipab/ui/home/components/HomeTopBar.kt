// File: ui/home/components/HomeTopBar.kt (Update)
package com.example.responsipab.ui.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.responsipab.data.auth.AuthUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    authUiState: AuthUiState,
    cartItemCount: Int = 0,
    onCartClick: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Column {
                if (authUiState is AuthUiState.Authenticated) {
                    Text(
                        text = "Selamat Datang, ${authUiState.user.name}! 👋",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Text(
                    text = "Rental Kamera",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        actions = {
            when (authUiState) {
                is AuthUiState.Authenticated -> {
                    TextButton(onClick = onLogoutClick) {
                        Text("Logout")
                    }
                    CartBadge(
                        itemCount = cartItemCount,
                        onClick = onCartClick
                    )
                }
                is AuthUiState.Unauthenticated -> {
                    TextButton(onClick = onLoginClick) {
                        Text("Login")
                    }
                }
                is AuthUiState.Loading -> {}
            }
        },
        modifier = modifier
    )
}