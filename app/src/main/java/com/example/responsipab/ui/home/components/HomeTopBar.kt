package com.example.responsipab.ui.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar() {
    TopAppBar(
        title = {
            Text("KameraKu Rental", fontWeight = FontWeight.Bold)
        },
        actions = {
            IconButton(onClick = { /* TODO: Notifikasi */ }) {
                Icon(Icons.Default.Notifications, contentDescription = "Notifikasi")
            }
            IconButton(onClick = { /* TODO: Profil */ }) {
                Icon(Icons.Default.Person, contentDescription = "Profil")
            }
        }
    )
}