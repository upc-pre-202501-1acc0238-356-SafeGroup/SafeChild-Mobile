package com.example.safechild.model.response

import com.example.safechild.model.beans.profiles.Caregiver
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.PUT

interface ProfileApiService {

    @GET("api/v1/caregiver")
    suspend fun getCaregivers(): Response<List<Caregiver>>

    @GET("api/v1/caregiver/{id}")
    suspend fun getCaregiverId(@Path("id")id:Int): Response<Caregiver>

    // Crear caregiver
    @POST("api/v1/caregiver")
    suspend fun createCaregiver(@Body caregiver: Caregiver): Response<Caregiver>

    // Actualizar caregiver
    @PUT("api/v1/caregiver/{caregiverId}")
    suspend fun updateCaregiver(@Path("caregiverId") caregiverId: Long, @Body caregiver: Caregiver): Response<Caregiver>


}