package com.example.responsipab.data

import com.example.rentalkamera.R
import com.example.responsipab.model.Camera

object CameraRepository {
    val cameraList = listOf(
        Camera(1, "Canon EOS 5D Mark IV", 250000, 4.8f, R.drawable.camera_1),
        Camera(2, "Sony Alpha A7 III", 300000, 4.9f, R.drawable.camera_2),
        Camera(3, "Nikon Z6", 280000, 4.7f, R.drawable.camera_3),
        Camera(4, "Fujifilm X-T4", 270000, 4.6f, R.drawable.camera_4),
        Camera(5, "Canon EOS R5", 350000, 4.9f, R.drawable.camera_5),
        Camera(6, "Sony Alpha A7S III", 320000, 4.8f, R.drawable.camera_6),
        Camera(7, "Nikon Z7 II", 330000, 4.7f, R.drawable.camera_7),
        Camera(8, "Panasonic Lumix S5", 290000, 4.6f, R.drawable.camera_8),
        Camera(9, "Fujifilm GFX 100S", 400000, 4.9f, R.drawable.camera_9),
        Camera(10, "Olympus OM-D E-M1 Mark III", 260000, 4.5f, R.drawable.camera_10)
    )

    fun getCameraById(id: Int?): Camera? = cameraList.find { it.id == id }
}