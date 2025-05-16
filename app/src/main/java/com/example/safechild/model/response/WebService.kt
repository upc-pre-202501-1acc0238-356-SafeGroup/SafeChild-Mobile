package com.example.safechild.model.response

import com.example.safechild.model.beans.PaymentMethod
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {
    @GET("PaymentMethods/{id}")
    suspend fun getPaymentMethodId(@Path("id")id: Int): Response<PaymentMethod>
}