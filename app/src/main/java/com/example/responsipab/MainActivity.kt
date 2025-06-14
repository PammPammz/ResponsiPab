package com.example.responsipab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.responsipab.navigation.RentalCameraNavGraph
import com.example.responsipab.ui.shared.theme.RentalKameraTheme
import com.example.responsipab.ui.viewmodel.CartViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RentalKameraTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RentalKameraApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RentalKameraApp() {
    val navController = rememberNavController()

    // Inisialisasi CartViewModel di level app untuk sharing state
    val cartViewModel: CartViewModel = viewModel()

    RentalCameraNavGraph(
        navController = navController,
        cartViewModel = cartViewModel
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RentalKameraTheme {
        RentalKameraApp()
    }
}