package com.example.responsipab

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import com.example.responsipab.ui.shared.theme.RentalKameraTheme

class LoadingScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RentalKameraTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF1976D2) // Sesuaikan dengan warna tema Anda
                ) {
                    LoadingContent {
                        // Setelah delay selesai, lanjut ke MainActivity
                        startActivity(Intent(this@LoadingScreen, MainActivity::class.java))
                        finish()
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingContent(onTimeout: () -> Unit) {
    // Delay untuk simulasi loading selama 2 detik
    LaunchedEffect(Unit) {
        delay(2000)
        onTimeout()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo atau teks aplikasi
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Rental",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
            Text(
                text = "Camera",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFFFFC107) // Ubah warna sesuai kebutuhan
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Indikator progress loading
        CircularProgressIndicator(color = Color(0xFFFFC107)) // Ubah warna sesuai kebutuhan
    }
}