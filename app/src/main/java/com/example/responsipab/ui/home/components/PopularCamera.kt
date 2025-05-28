package com.example.responsipab.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rentalkamera.R
import com.example.responsipab.model.Camera

@Composable
fun PopularCameraSection(onCameraClick: (Camera) -> Unit) {
    val popularCameras = listOf(
        Camera(1, "Canon EOS 5D Mark IV", 250000, 4.8f, R.drawable.camera_1),
        Camera(2, "Sony Alpha A7 III", 300000, 4.9f, R.drawable.camera_2),
        Camera(3, "Nikon Z6", 280000, 4.7f, R.drawable.camera_3),
        Camera(4, "Fujifilm X-T4", 270000, 4.6f, R.drawable.camera_4)
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        items(popularCameras) { camera ->
            CameraCard(camera, onCameraClick = onCameraClick)
        }
    }
}