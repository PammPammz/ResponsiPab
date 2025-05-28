package com.example.responsipab.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rentalkamera.R
import com.example.responsipab.model.Camera

@Composable
fun NewCameraSection(onCameraClick: (Camera) -> Unit) {
    val newCameras = listOf(
        Camera(5, "Canon EOS R5", 350000, 4.9f, R.drawable.camera_5),
        Camera(6, "Sony Alpha A7S III", 320000, 4.8f, R.drawable.camera_6),
        Camera(7, "Nikon Z7 II", 330000, 4.7f, R.drawable.camera_7),
        Camera(8, "Panasonic Lumix S5", 290000, 4.6f, R.drawable.camera_8),
        Camera(9, "Fujifilm GFX 100S", 400000, 4.9f, R.drawable.camera_9),
        Camera(10, "Olympus OM-D E-M1 Mark III", 260000, 4.5f, R.drawable.camera_10)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.height(420.dp)
    ) {
        items(newCameras) { camera ->
            CameraCard(camera, isGrid = true, onCameraClick = onCameraClick)
        }
    }
}