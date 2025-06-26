package com.example.responsipab.ui.checkout


import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.responsipab.data.cart.CartItem
import com.example.responsipab.data.cart.CartState
import com.example.responsipab.data.cart.CartViewModel
import com.example.responsipab.data.order.CheckoutEvent
import com.example.responsipab.data.order.CheckoutViewModel
import com.example.responsipab.ui.shared.utils.formatPrice
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    navController: NavController,
    viewModel: CheckoutViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val formState = viewModel.formState
    val cartState by cartViewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is CheckoutEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                is CheckoutEvent.NavigateToOrderSuccess -> {
                    navController.navigate("orders")
                }
                else -> {}
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Checkout") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
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
            OutlinedTextField(value = formState.fullName, onValueChange = viewModel::onFullNameChange, label = { Text("Full Name") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = formState.email, onValueChange = viewModel::onEmailChange, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = formState.phone, onValueChange = viewModel::onPhoneChange, label = { Text("Phone Number") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = formState.address, onValueChange = viewModel::onAddressChange, label = { Text("Address") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = formState.notes, onValueChange = viewModel::onNotesChange, label = { Text("Special Requests / Notes") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = formState.purpose, onValueChange = viewModel::onPurposeChange, label = { Text("Purpose") }, modifier = Modifier.fillMaxWidth())

            Spacer(Modifier.height(16.dp))

            Text("Delivery Method", style = MaterialTheme.typography.titleMedium)
            Row {
                RadioButton(selected = formState.deliveryMethod == "delivery", onClick = { viewModel.onDeliveryMethodChange("delivery") })
                Text("Delivery", modifier = Modifier.align(Alignment.CenterVertically))
                Spacer(Modifier.width(16.dp))
                RadioButton(selected = formState.deliveryMethod == "pickup", onClick = { viewModel.onDeliveryMethodChange("pickup") })
                Text("Store Pickup", modifier = Modifier.align(Alignment.CenterVertically))
            }

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(value = formState.startDate, onValueChange = viewModel::onStartDateChange, label = { Text("Rental Start Date (YYYY-MM-DD)") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = formState.endDate, onValueChange = viewModel::onEndDateChange, label = { Text("Rental End Date (YYYY-MM-DD)") }, modifier = Modifier.fillMaxWidth())

            Spacer(Modifier.height(24.dp))

            Divider(modifier = Modifier.padding(vertical = 16.dp))

            if (cartState is CartState.Success && (cartState as CartState.Success).items.isNotEmpty()) {
                CheckoutSummarySection(cartItems = (cartState as CartState.Success).items)
            }

            Spacer(Modifier.height(16.dp))
            Button(
                onClick = viewModel::submitOrder,
                enabled = !viewModel.isSubmitting,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                if (viewModel.isSubmitting) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
                } else {
                    Text("Submit Order")
                }
            }
        }
    }
}

@Composable
private fun CheckoutSummarySection(
    cartItems: List<CartItem>,
    modifier: Modifier = Modifier
) {
    val totalPrice = cartItems.sumOf { it.equipment.price * it.quantity }

    Column(modifier = modifier) {
        Text("Equipment Summary", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

        cartItems.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = item.equipment.name, style = MaterialTheme.typography.bodyLarge)
                    Text(text = "x${item.quantity}", style = MaterialTheme.typography.bodyMedium)
                }
                Text(
                    text = formatPrice(item.equipment.price * item.quantity),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Total", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(
                text = formatPrice(totalPrice),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
