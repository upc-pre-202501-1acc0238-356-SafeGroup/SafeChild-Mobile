package com.example.safechild.network

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
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


    @GET("api/v1/caregiver")
    suspend fun getCaregivers(): Response<List<Caregiver>>


    @GET("api/v1/caregiver/{id}")
    suspend fun getCaregiverId(@Path("id")id:Int): Response<Caregiver>

    @GET("api/v1/caregiver-schedule/{id}")
    suspend fun getScheduleId(@Path("id") id: Int): Response<List<Schedule>>

}
