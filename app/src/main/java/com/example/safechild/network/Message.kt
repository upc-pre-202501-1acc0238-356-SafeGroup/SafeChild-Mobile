package com.example.safechild.network

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
    @SerializedName("completeName") val completeName: CompleteName
)

data class CompleteName(
    @SerializedName("completeName") val name: String
)
