package com.example.safechild.model.beans.payments

data class Payment(
    val id: Long,
    val currency: String,
    val paymentStatus: String,
    val amount: Int,
    val reservation: Int,
    val caregiverId: Int,
    val tutorId: Int,
    val stripePaymentId: String
)