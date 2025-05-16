package com.example.safechild.network

import retrofit2.Call
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


    @GET("api/v1/tutors")
    fun getTutors(): Call<List<Tutor>>

    @GET("api/v1/tutors/{id}")
    fun getTutorById(
        @Path("id") id: Long
    ): Call<Tutor>

    @GET("api/v1/reservations/{tutorId}/{caregiverId}")
    fun getReservations(
        @Path("tutorId") tutorId: Long,
        @Path("caregiverId") caregiverId: Long
    ): Call<List<Reservation>>
}
