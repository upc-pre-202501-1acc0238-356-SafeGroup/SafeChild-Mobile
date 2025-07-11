package com.example.safechild.model.response

import com.example.safechild.model.beans.schedules.Schedule
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface SchedulesApiService {

    @GET("api/v1/schedules/{id}")
    suspend fun getScheduleById(@Path("id") id: Int): Response<List<Schedule>>


    @GET("api/v1/schedules/caregiver/{caregiverId}")
    suspend fun getSchedulesByCaregiverId(@Path("caregiverId") caregiverId: Long): Response<List<Schedule>>

    @POST("api/v1/schedules")
    suspend fun createSchedule(@Body schedule: Schedule): Response<Schedule>

    @PUT("api/v1/shifts/availability/{id}")
    suspend fun updateShiftAvailability(@Path("id") id: Long, @Body body: Map<String, Boolean>): Response<Unit>

    @DELETE("api/v1/schedules/{scheduleId}")
    suspend fun deleteSchedule(@Path("scheduleId") scheduleId: Long): Response<Unit>

}