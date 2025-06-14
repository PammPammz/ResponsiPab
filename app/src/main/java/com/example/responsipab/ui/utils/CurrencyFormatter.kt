// File: ui/utils/CurrencyFormatter.kt (buat file baru)
package com.example.responsipab.ui.utils

import java.text.NumberFormat
import java.util.Locale

fun formatCurrency(amount: Double): String {
    return try {
        val format = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
        format.format(amount)
    } catch (e: Exception) {
        // Fallback jika NumberFormat gagal
        "Rp ${String.format("%,.0f", amount)}"
    }
}
