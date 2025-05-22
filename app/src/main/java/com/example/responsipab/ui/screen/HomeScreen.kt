package com.example.responsipab.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.responsipab.data.model.Equipment
import androidx.compose.foundation.lazy.items

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    equipmentList: List<Equipment>,
    onEquipmentClick: (Equipment) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Rental Kamera") }
            )
        }
    ) { padding ->
        Column(modifier = Modifier
            .padding(padding)
            .padding(16.dp)
        ) {
            Text(
                text = "Pilih Peralatan yang Tersedia",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            LazyColumn {
                items(equipmentList) { equipment ->
                    EquipmentCard(equipment = equipment, onClick = { onEquipmentClick(equipment) })
                }
            }
        }
    }
}

@Composable
fun EquipmentCard(
    equipment: Equipment,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = equipment.imageRes),
                contentDescription = equipment.name,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = equipment.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Rp ${equipment.pricePerDay}/hari",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}