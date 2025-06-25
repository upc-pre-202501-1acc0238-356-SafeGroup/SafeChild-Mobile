package com.example.safechild.network.entities.iam

data class SignInResponse(
    val id: Long,
    val username: String,
    val roles: List<String>,
    val token: String
)