package com.example.safechild.model.client

import com.example.safechild.model.client.interceptors.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseRetrofitClient {

    companion object {
        //const val BASE_URL = "http://192.168.18.21:8093/"
        const val BASE_URL = "https://safechild-lastdeploy-a8ahd7ccc8b4cjb3.canadacentral-01.azurewebsites.net/"

        val retrofit: Retrofit by lazy {
            val okHttpClient = OkHttpClient.Builder()
                .build()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
    }
}