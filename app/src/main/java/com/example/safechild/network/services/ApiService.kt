package com.example.safechild.network.services

import com.example.safechild.network.entities.Caregiver
import com.example.safechild.network.entities.Message
import com.example.safechild.network.room.model.beans.PaymentMethod
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

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

    @POST("api/v1/caregiver")
    fun createCaregiver(@Body caregiver: Caregiver): Call<Caregiver>

    @GET("api/v1/caregiver")
    fun getCaregivers(): Call<List<Caregiver>>

    @GET("PaymentMethods/{id}")
    suspend fun getPaymentMethodId(@Path("id")id: Int): Response<PaymentMethod>
}