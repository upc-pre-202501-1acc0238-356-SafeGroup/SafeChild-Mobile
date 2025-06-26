package com.example.safechild.network.retrofit

import com.example.safechild.network.AuthInterceptor
import com.example.safechild.network.retrofit.RetrofitClient.BASE_URL
import com.example.safechild.network.retrofit.RetrofitClient.apiService
import com.example.safechild.network.services.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    //private const val BASE_URL = "https://safechild-lastdeploy-a8ahd7ccc8b4cjb3.canadacentral-01.azurewebsites.net/"
    private const val BASE_URL = "http://10.0.2.2:8080/"


    @Volatile
    private var token: String? = null

    @Volatile
    private var retrofit: Retrofit? = null

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor { token })
            .build()
    }

    fun updateToken(newToken: String?) {
        token = newToken
        retrofit = null // Fuerza recrear Retrofit con el nuevo token
    }

    private fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build()
        }
        return retrofit!!
    }

    val apiService: ApiService
        get() = getRetrofit().create(ApiService::class.java)


}

