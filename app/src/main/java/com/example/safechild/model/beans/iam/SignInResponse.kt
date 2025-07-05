package com.example.safechild.model.beans.iam

data class SignInResponse(
    val id: Long,
    val username: String,
    val roles: List<String>,
    val token: String
)