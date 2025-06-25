package com.example.safechild.network.entities.iam

data class SignUpRequest(
    val username: String,
    val password: String,
    val roles: List<String>
)