package com.example.safechild.model.response

import com.example.safechild.model.beans.iam.SignInRequest
import com.example.safechild.model.beans.iam.SignInResponse
import com.example.safechild.model.beans.iam.SignUpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("api/v1/authentication/sign-in")
    suspend fun signIn(@Body request: SignInRequest): Response<SignInResponse>

    // Registro de usuario (sign up)
    @POST("api/v1/authentication/sign-up")
    suspend fun signUp(@Body request: SignUpRequest): Response<Void>

}