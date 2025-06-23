package com.example.safechild.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.safechild.network.services.ApiService

object RetrofitClient {

    private const val BASE_URL = "http://192.168.1.23:8080/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor()) // Agrega el interceptor aquí
        .build()

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
}
