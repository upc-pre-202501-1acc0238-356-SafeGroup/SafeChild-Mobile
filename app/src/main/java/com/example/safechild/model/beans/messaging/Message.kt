package com.example.safechild.model.beans.messaging

import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("id") val id: Long,
    @SerializedName("sender") val sender: Person,
    @SerializedName("receiver") val receiver: Person,
    @SerializedName("content") val content: String,
    @SerializedName("timestamp") val timestamp: String
)

data class Person(
    @SerializedName("id") val id: Long,
    @SerializedName("username") val username: String
)
