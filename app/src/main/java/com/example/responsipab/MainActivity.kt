package com.example.responsipab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.responsipab.data.model.Equipment
import com.example.responsipab.ui.screen.HomeScreen
import com.example.responsipab.ui.theme.ResponsiPabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val dummyEquipment = listOf(
            Equipment(1, "Canon EOS R5", 150000, R.drawable.camera_1),
            Equipment(2, "Sony A7 III", 120000, R.drawable.camera_1),
            Equipment(3, "Fujifilm X-T4", 100000, R.drawable.camera_1)
        )

        setContent {
            ResponsiPabTheme {
                HomeScreen(dummyEquipment) { }
            }
        }
    }
}

