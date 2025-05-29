package com.example.responsipab.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rentalkamera.R

@Composable
fun CategorySection(
    categories: List<Pair<String, Int>> = listOf(
        "DSLR" to R.drawable.ic_dslr,
        "Mirrorless" to R.drawable.ic_mirrorless,
        "Action Cam" to R.drawable.ic_action_cam,
        "Lensa" to R.drawable.ic_lens,
        "Aksesoris" to R.drawable.ic_accessories
    ),
    onCategoryClick: (String) -> Unit = {}
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        items(categories) { (name, iconRes) ->
            CategoryItem(
                name = name,
                iconRes = iconRes,
                onClick = { onCategoryClick(name) }
            )
        }
    }
}