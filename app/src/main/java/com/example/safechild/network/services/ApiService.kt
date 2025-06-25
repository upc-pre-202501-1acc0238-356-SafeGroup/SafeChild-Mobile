package com.example.safechild.network.services

import com.example.safechild.network.entities.Caregiver
import com.example.safechild.network.entities.Message
import com.example.safechild.network.entities.Schedule
import com.example.safechild.network.entities.iam.SignInRequest
import com.example.safechild.network.entities.iam.SignInResponse
import com.example.safechild.network.entities.iam.SignUpRequest
import com.example.safechild.network.room.model.beans.PaymentMethod
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.PUT

interface ApiService {
    @GET("api/v1/messages/chats/{userId}")
    fun getChats(
        @Path("userId") userId: Long
    ): Call<List<Message>>

    @GET("api/v1/messages/{senderId}/{receiverId}")
    fun getMessages(
        @Path("senderId") senderId: Long,
        @Path("receiverId") receiverId: Long
    ): Call<List<Message>>

    @GET("api/v1/caregiver")
    suspend fun getCaregivers(): Response<List<Caregiver>>


    @GET("api/v1/caregiver/{id}")
    suspend fun getCaregiverId(@Path("id")id:Int): Response<Caregiver>

    @GET("api/v1/caregiver-schedule/{id}")
    suspend fun getScheduleId(@Path("id") id: Int): Response<List<Schedule>>

    @GET("PaymentMethods/{id}")
    suspend fun getPaymentMethodId(@Path("id")id: Int): Response<PaymentMethod>

    @POST("api/v1/authentication/sign-in")
    suspend fun signIn(@Body request: SignInRequest): Response<SignInResponse>

    // Registro de usuario (sign up)
    @POST("api/v1/authentication/sign-up")
    suspend fun signUp(@Body request: SignUpRequest): Response<Void>

    // Crear caregiver
    @POST("api/v1/caregiver")
    suspend fun createCaregiver(@Body caregiver: Caregiver): Response<Caregiver>

    // Actualizar caregiver
    @PUT("api/v1/caregiver/{caregiverId}")
    suspend fun updateCaregiver(@Path("caregiverId") caregiverId: Long, @Body caregiver: Caregiver): Response<Caregiver>


}