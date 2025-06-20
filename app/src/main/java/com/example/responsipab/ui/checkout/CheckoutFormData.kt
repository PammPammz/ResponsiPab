package com.example.responsipab.ui.checkout

data class CheckoutFormData(
    val fullName: String,
    val email: String,
    val phone: String,
    val address: String,
    val notes: String,
    val purpose: String,
    val deliveryMethod: String,
    val startDate: String,
    val endDate: String
)