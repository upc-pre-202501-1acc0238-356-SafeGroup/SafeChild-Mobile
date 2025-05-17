package com.example.safechild.network

data class Caregiver(
    val id: Long,
    val completeName: String,
    val age: Long,
    val address: String,
    val caregiverExperience: Long,
    val completedServices: Long,
    val biography: String,
    val profileImage: String,
    val farePerHour: Long,
    val districtsScope: String,
    val profileId: Long
)

