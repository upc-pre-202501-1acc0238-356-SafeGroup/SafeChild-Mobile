package com.example.safechild.model.response

import com.example.safechild.model.beans.messaging.Message
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MessagingApiService {
    @GET("api/v1/messages/chats/{userId}")
    fun getChats(
        @Path("userId") userId: Long
    ): Call<List<Message>>

    @GET("api/v1/messages/{senderId}/{receiverId}")
    fun getMessages(
        @Path("senderId") senderId: Long,
        @Path("receiverId") receiverId: Long
    ): Call<List<Message>>

}