package com.example.responsipab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.responsipab.model.Camera
import com.example.responsipab.ui.camera_detail.CameraDetailScreen
import com.example.responsipab.ui.home.HomeScreen
import com.example.responsipab.ui.shared.theme.RentalKameraTheme

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
    var selectedCamera by remember { mutableStateOf<Camera?>(null) }

    if (selectedCamera != null) {
        CameraDetailScreen(
            camera = selectedCamera!!,
            onBack = { selectedCamera = null }
        )
    } else {
        HomeScreen(onCameraClick = { camera -> selectedCamera = camera })
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RentalKameraTheme {
        RentalKameraApp()
    }
}