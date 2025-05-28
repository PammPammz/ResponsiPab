package com.example.responsipab.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.responsipab.data.CameraRepository
import com.example.responsipab.model.Camera

@Composable
fun PopularCameraSection(onCameraClick: (Camera) -> Unit) {
    val popularCameras = CameraRepository.cameraList.take(4)

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        items(popularCameras) { camera ->
            CameraCard(camera, onCameraClick = onCameraClick)
        }
    }
}