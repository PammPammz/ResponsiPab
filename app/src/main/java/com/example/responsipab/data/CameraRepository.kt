package com.example.responsipab.data

import com.example.rentalkamera.R
import com.example.responsipab.data.model.Camera

object CameraRepository {
    val cameraList = listOf(
        Camera(1, "Canon EOS 5D Mark IV", 250000.0, 4.8f, "https://example.com/canon-5d-mark-iv.jpg","Canon",R.drawable.camera_1),
        Camera(2, "Sony Alpha A7 III", 300000.0, 4.9f, "https://example.com/sony-a7-iii.jpg","Sony",R.drawable.camera_2),
        Camera(3, "Nikon Z6", 280000.0, 4.7f,"https://example.com/nikon-z6.jpg", "Nikon",R.drawable.camera_3),
        Camera(4, "Fujifilm X-T4", 270000.0, 4.6f,"https://example.com/fujifilm-xt4.jpg","Fujifilm", R.drawable.camera_4),
        Camera(5, "Canon EOS R5", 350000.0, 4.9f,"https://example.com/canon-r5.jpg","Canon", R.drawable.camera_5),
        Camera(6, "Sony Alpha A7S III", 320000.0, 4.8f,"https://example.com/sony-a7s-iii.jpg","Sony", R.drawable.camera_6),
        Camera(7, "Nikon Z7 II", 330000.0, 4.7f, "https://example.com/nikon-z7-ii.jpg","Nikon",R.drawable.camera_7),
        Camera(8, "Panasonic Lumix S5", 290000.0, 4.6f, "https://example.com/panasonic-s5.jpg","Panasonic",R.drawable.camera_8),
        Camera(9, "Fujifilm GFX 100S", 400000.0, 4.9f, "https://example.com/fujifilm-gfx-100s.jpg","Fujifilm",R.drawable.camera_9),
        Camera(10, "Olympus OM-D E-M1 Mark III", 260000.0, 4.5f, "https://example.com/olympus-em1-iii.jpg","Olympus",R.drawable.camera_10)
    )

    fun getCameraById(id: Int?): Camera? = cameraList.find { it.id == id }
}