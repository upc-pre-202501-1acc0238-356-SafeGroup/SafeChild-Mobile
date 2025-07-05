package com.example.safechild.model.client

import com.example.safechild.model.client.interceptors.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseRetrofitClient {

    companion object {
        const val BASE_URL = "http://192.168.18.21:8093/"

        @Volatile
        private var token: String? = null

        fun updateToken(newToken: String?) {
            token = newToken
        }

        val retrofit: Retrofit by lazy {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor { token })
                .build()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
    }
}