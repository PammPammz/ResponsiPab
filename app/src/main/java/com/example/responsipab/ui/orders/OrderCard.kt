package com.example.responsipab.ui.orders

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.responsipab.data.model.Order

@Composable
fun OrderCard(
    order: Order,
    onClick: () -> Unit
) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Order ID: ${order.id}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Status: ${order.status.capitalize()}",
                color = when (order.status) {
                    "pending" -> Color.Gray
                    "approved" -> Color(0xFF4CAF50)
                    "rejected" -> Color.Red
                    "cancelled" -> Color.DarkGray
                    "completed" -> Color(0xFF2196F3)
                    else -> Color.Black
                },
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "Rental Period: ${order.startDate} → ${order.endDate}",
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = "Total Price: Rp ${order.totalPrice}",
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = "Items: ${order.items.joinToString { "${it.name} (x${it.quantity})" }}",
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = "Date Ordered: ${order.dateOrdered}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}
