package com.example.responsipab.ui.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.responsipab.data.order.Order
import com.example.responsipab.ui.shared.utils.formatPrice
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

private fun formatIsoDate(isoString: String): String {
    return try {
        val zonedDateTime = ZonedDateTime.parse(isoString)
        val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.getDefault())
        zonedDateTime.format(formatter)
    } catch (e: Exception) {
        isoString.take(10)
    }
}

@Composable
fun OrderCard(
    order: Order,
) {
    val statusColor = when (order.status.lowercase()) {
        "pending" -> Color(0xFFFFA726)
        "approved" -> Color(0xFF66BB6A)
        "rejected" -> MaterialTheme.colorScheme.error
        else -> Color.Gray
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Order #${order.id}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(statusColor.copy(alpha = 0.15f))
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    text = order.status.replaceFirstChar { it.uppercase() },
                    color = statusColor,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Divider(modifier = Modifier.padding(vertical = 8.dp))

        Text(text = "Total: ${formatPrice(order.total)}", fontWeight = FontWeight.SemiBold)
        Text(text = "Items: ${order.items.joinToString { it.equipment.name }}", style = MaterialTheme.typography.bodySmall)
        Text(text = "Rental: ${formatIsoDate(order.rentalStart)} to ${formatIsoDate(order.rentalEnd)}", style = MaterialTheme.typography.bodySmall)
        Text(text = "Ordered on: ${formatIsoDate(order.createdAt)}", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
    }
}
