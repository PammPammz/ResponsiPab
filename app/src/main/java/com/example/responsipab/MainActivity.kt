package com.example.rentalkamera

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ResponsiPab.ui.theme.RentalKameraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RentalKameraTheme {
                // A surface container using the 'background' color from the theme
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
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "KameraKu Rental",
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(onClick = { /* TODO: Implementasi notifikasi */ }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifikasi"
                        )
                    }
                    IconButton(onClick = { /* TODO: Implementasi profil */ }) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profil"
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { /* TODO: Navigasi ke Home */ },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /* TODO: Navigasi ke Explore */ },
                    icon = { Icon(Icons.Default.Search, contentDescription = "Explore") },
                    label = { Text("Explore") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /* TODO: Navigasi ke Booking */ },
                    icon = { Icon(Icons.Default.DateRange, contentDescription = "Booking") },
                    label = { Text("Booking") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /* TODO: Navigasi ke Favorit */ },
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorit") },
                    label = { Text("Favorit") }
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                // Search Bar
                SearchBar()
            }

            item {
                // Banner Promo
                PromoSection()
            }

            item {
                // Kategori Kamera
                Text(
                    "Kategori Kamera",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                KategoriSection()
            }

            item {
                // Kamera Populer
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Kamera Populer",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    TextButton(onClick = { /* TODO: Lihat semua kamera populer */ }) {
                        Text("Lihat Semua")
                    }
                }
                PopularCameraSection()
            }

            item {
                // Kamera Terbaru
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Kamera Terbaru",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    TextButton(onClick = { /* TODO: Lihat semua kamera terbaru */ }) {
                        Text("Lihat Semua")
                    }
                }
                NewCameraSection()
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        placeholder = { Text("Cari kamera...") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon"
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(12.dp)
    )
}

@Composable
fun PromoSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable { /* TODO: Implementasi aksi klik promo */ },
        shape = RoundedCornerShape(12.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Placeholder untuk gambar promo
            // Dalam aplikasi nyata, gunakan coil atau glide untuk memuat gambar dari URL
            Image(
                painter = painterResource(id = R.drawable.promo_placeholder),
                contentDescription = "Promo Banner",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    "Diskon 20% untuk Rental Pertama",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    "Berlaku sampai 30 Juni 2023",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun KategoriSection() {
    val categories = listOf(
        "DSLR" to R.drawable.ic_dslr,
        "Mirrorless" to R.drawable.ic_mirrorless,
        "Action Cam" to R.drawable.ic_action_cam,
        "Lensa" to R.drawable.ic_lens,
        "Aksesoris" to R.drawable.ic_accessories
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        items(categories) { (name, iconRes) ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(80.dp)
            ) {
                Card(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .clickable { /* TODO: Navigasi ke kategori */ },
                    shape = CircleShape
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = iconRes),
                            contentDescription = name,
                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = name,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

data class Camera(
    val id: Int,
    val name: String,
    val price: Int,
    val rating: Float,
    val imageRes: Int
)

@Composable
fun PopularCameraSection() {
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
            CameraCard(camera)
        }
    }
}

@Composable
fun NewCameraSection() {
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
            CameraCard(camera, isGrid = true)
        }
    }
}

@Composable
fun CameraCard(camera: Camera, isGrid: Boolean = false) {
    val cardWidth = if (isGrid) Modifier.fillMaxWidth() else Modifier.width(180.dp)

    Card(
        modifier = cardWidth
            .clickable { /* TODO: Navigasi ke detail kamera */ },
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {
            // Placeholder untuk gambar kamera
            Image(
                painter = painterResource(id = camera.imageRes),
                contentDescription = camera.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )

            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = camera.name,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Menggunakan formatPrice untuk menampilkan harga
                Text(
                    text = "Rp ${formatPrice(camera.price)}/Hari",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = camera.rating.toString(),
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

fun formatPrice(price: Int): String {
    return if (price >= 1000) {
        val formattedPrice = price / 1000
        "${formattedPrice}K"
    } else {
        price.toString()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RentalKameraTheme {
        RentalKameraApp()
    }
}