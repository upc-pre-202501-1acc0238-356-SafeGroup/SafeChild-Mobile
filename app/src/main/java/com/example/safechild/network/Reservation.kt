package com.example.safechild.network

data class Reservation(
    val id: Long,
    val caregiverId: Long,
    val tutorId: Long,
    val date: String,
    val startTime: String,
    val endTime: String,
    val status: String,
    val totalAmount: Double,
)