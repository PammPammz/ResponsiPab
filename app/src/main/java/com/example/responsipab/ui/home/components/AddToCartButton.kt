package com.example.responsipab.ui.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.responsipab.data.model.Camera
import com.example.responsipab.ui.shared.utils.formatPrice

@Composable
fun AddToCartButton(
    camera: Camera,
    onAddToCart: (Camera, Int, Int) -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false
) {
    var quantity by remember { mutableIntStateOf(1) }
    var rentalDays by remember { mutableIntStateOf(1) }
    var showDialog by remember { mutableStateOf(false) }

    Button(
        onClick = { showDialog = true },
        modifier = modifier,
        enabled = camera.isAvailable && !isLoading
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(16.dp),
                strokeWidth = 2.dp
            )
        } else {
            Icon(
                Icons.Default.ShoppingCart,
                contentDescription = "Add to Cart",
                modifier = Modifier.size(18.dp)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = if (camera.isAvailable) "Tambah ke Keranjang" else "Tidak Tersedia"
        )
    }

    if (showDialog) {
        AddToCartDialog(
            camera = camera,
            quantity = quantity,
            rentalDays = rentalDays,
            onQuantityChange = { quantity = it },
            onRentalDaysChange = { rentalDays = it },
            onConfirm = {
                onAddToCart(camera, quantity, rentalDays)
                showDialog = false
            },
            onDismiss = { showDialog = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddToCartDialog(
    camera: Camera,
    quantity: Int,
    rentalDays: Int,
    onQuantityChange: (Int) -> Unit,
    onRentalDaysChange: (Int) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Tambah ke Keranjang")
        },
        text = {
            Column {
                Text(
                    text = camera.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Quantity Selector
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Jumlah:")
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { if (quantity > 1) onQuantityChange(quantity - 1) }
                        ) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Kurangi")
                        }
                        Text(
                            text = quantity.toString(),
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        IconButton(
                            onClick = { onQuantityChange(quantity + 1) }
                        ) {
                            Icon(Icons.Default.Add, contentDescription = "Tambah")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Rental Days Selector
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Lama Sewa (hari):")
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { if (rentalDays > 1) onRentalDaysChange(rentalDays - 1) }
                        ) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Kurangi")
                        }
                        Text(
                            text = rentalDays.toString(),
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        IconButton(
                            onClick = { onRentalDaysChange(rentalDays + 1) }
                        ) {
                            Icon(Icons.Default.Add, contentDescription = "Tambah")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Total Price
                val totalPrice = camera.price * quantity * rentalDays
                Text(
                    text = "Total: Rp ${formatPrice(totalPrice.toInt().toDouble())}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Tambah ke Keranjang")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Batal")
            }
        }
    )
}