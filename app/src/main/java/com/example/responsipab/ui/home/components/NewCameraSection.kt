package com.example.responsipab.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.responsipab.data.CameraRepository
import com.example.responsipab.model.Camera

@Composable
fun NewCameraSection(onCameraClick: (Camera) -> Unit) {
    val newCameras = CameraRepository.cameraList

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