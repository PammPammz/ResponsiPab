package com.example.responsipab.ui.checkout


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.responsipab.data.model.CartItem
import com.example.responsipab.ui.checkout.CheckoutFormData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    cartItems: List<CartItem>,
    onSubmit: (CheckoutFormData) -> Unit,
    onBack: () -> Unit
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var purposes by remember { mutableStateOf("") }

    var deliveryMethod by remember { mutableStateOf("Delivery") }

    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Checkout") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text("Full Name") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone Number") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Address") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Special Requests / Notes") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = purposes,
                onValueChange = { purposes = it },
                label = { Text("Purposes") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            Text("Delivery Method", style = MaterialTheme.typography.titleMedium)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    RadioButton(
                        selected = deliveryMethod == "Delivery",
                        onClick = { deliveryMethod = "Delivery" }
                    )
                    Text("Delivery")
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = deliveryMethod == "Store Pickup",
                        onClick = { deliveryMethod = "Store Pickup" }
                    )
                    Text("Store Pickup")
                }
            }

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = startDate,
                onValueChange = { startDate = it },
                label = { Text("Rental Start Date (YYYY-MM-DD)") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = endDate,
                onValueChange = { endDate = it },
                label = { Text("Rental End Date (YYYY-MM-DD)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            Divider(modifier = Modifier.padding(vertical = 16.dp))

            Text("Equipment Summary", style = MaterialTheme.typography.titleMedium)

            cartItems.forEach { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = item.camera.name, style = MaterialTheme.typography.bodyMedium)
                        Text(text = "x${item.quantity}", style = MaterialTheme.typography.bodySmall)
                    }
                    Text(
                        text = "Rp${item.totalPrice.toInt()}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Divider(modifier = Modifier.padding(vertical = 8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Total", style = MaterialTheme.typography.titleMedium)
                Text("Rp${250000.toInt()}", style = MaterialTheme.typography.titleMedium)
            }

            Button(
                onClick = {
                    onSubmit(
                        CheckoutFormData(
                            fullName, email, phone, address, notes,
                            purposes, deliveryMethod, startDate, endDate
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit Order")
            }
        }
    }
}
