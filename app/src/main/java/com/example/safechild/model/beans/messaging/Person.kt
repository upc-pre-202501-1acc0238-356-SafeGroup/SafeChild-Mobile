package com.example.safechild.model.beans.messaging

import com.google.gson.annotations.SerializedName

data class Person(
    @SerializedName("id") val id: Long,
    @SerializedName("username") val username: String
)
