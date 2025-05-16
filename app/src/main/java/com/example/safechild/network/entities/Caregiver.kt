package com.example.safechild.network.entities

import com.google.gson.annotations.SerializedName

data class Caregiver(
    @SerializedName("id") val id: Long,
    @SerializedName("email") val email: String,
    @SerializedName("completeName") val completeName: String,
    @SerializedName("age") val age: Int,
    @SerializedName("address") val address: String,
    @SerializedName("caregiverExperience") val caregiverExperience: Int,
    @SerializedName("completedServices") val completedServices: Int,
    @SerializedName("biography") val biography: String,
    @SerializedName("profileImage") val profileImage: String,
    @SerializedName("farePerHour") val farePerHour: Int,
    @SerializedName("districtsScope") val districtsScope: String,
    @SerializedName("profileId") val profileId: Long
)