package com.example.responsipab.ui.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeBottomBar() {
    NavigationBar {
        NavigationBarItem(
            selected = true,
            onClick = { /* TODO: Navigasi ke Home */ },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* TODO: Navigasi ke Explore */ },
            icon = { Icon(Icons.Default.Search, contentDescription = "Explore") },
            label = { Text("Explore") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* TODO: Navigasi ke Booking */ },
            icon = { Icon(Icons.Default.DateRange, contentDescription = "Booking") },
            label = { Text("Booking") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* TODO: Navigasi ke Favorit */ },
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorit") },
            label = { Text("Favorit") }
        )
    }
}