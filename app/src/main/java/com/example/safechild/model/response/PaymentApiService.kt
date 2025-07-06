package com.example.safechild.model.response

import com.example.safechild.model.beans.payments.Payment
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PaymentApiService {

    @GET("/api/v1/payments/caregiver/{caregiverId}/status/{paymentStatus}")
    suspend fun getPaymentsByCaregiverIdAndPaymentStatus(
        @Path("caregiverId") caregiverId: Long,
        @Path("paymentStatus") paymentStatus: String
    ): Response<List<Payment>>

}