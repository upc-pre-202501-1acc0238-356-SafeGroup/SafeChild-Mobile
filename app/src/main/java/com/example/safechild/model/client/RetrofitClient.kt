package com.example.safechild.model.client

import com.example.safechild.model.response.AuthApiService
import com.example.safechild.model.response.MessagingApiService
import com.example.safechild.model.response.PaymentApiService
import com.example.safechild.model.response.ProfileApiService
import com.example.safechild.model.response.SchedulesApiService

object RetrofitClient : BaseRetrofitClient() {

    val paymentApiService: PaymentApiService by lazy {
        retrofit.create(PaymentApiService::class.java)
    }

    val authApiService: AuthApiService by lazy {
        retrofit.create(AuthApiService::class.java)
    }

    val messagingApiService: MessagingApiService by lazy {
        retrofit.create(MessagingApiService::class.java)
    }

    val profileApiService: ProfileApiService by lazy {
        retrofit.create(ProfileApiService::class.java)
    }

    val schedulesApiService: SchedulesApiService by lazy {
        retrofit.create(SchedulesApiService::class.java)
    }


}