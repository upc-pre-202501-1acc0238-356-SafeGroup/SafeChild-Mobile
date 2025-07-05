package com.example.safechild.model.response

import com.example.safechild.model.beans.schedules.Schedule
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SchedulesApiService {

    @GET("api/v1/caregiver-schedule/{id}")
    suspend fun getScheduleId(@Path("id") id: Int): Response<List<Schedule>>

}