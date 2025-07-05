package com.example.safechild.model.beans.iam

data class SignUpRequest(
    val username: String,
    val password: String,
    val roles: List<String>
)