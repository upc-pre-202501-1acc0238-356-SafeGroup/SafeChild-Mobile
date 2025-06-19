package com.example.safechild.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmcmNnMjQyNCIsImlhdCI6MTc1MDMwMzU0MCwiZXhwIjoxNzUwOTA4MzQwfQ.TLv4GuzO2PM66GHRNSxjTEKisjDHmQ7T-UJB1eOrznI" // Reemplaza esto con tu token real

        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        return chain.proceed(request)
    }
}
