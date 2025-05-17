package com.example.safechild.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.safechild.network.Caregiver
import com.example.safechild.network.RetrofitClient
import com.example.safechild.network.RetrofitClient.apiService
import com.example.safechild.network.Schedule

class ServViewModel(): ViewModel() {

    var caregiver: Caregiver = Caregiver(
        id = 0,
        completeName = "",
        age = 0,
        address = "",
        caregiverExperience = 0,
        completedServices = 0,
        biography = "",
        profileImage = "",
        farePerHour = 0,
        districtsScope = "",
        profileId = 0
    )

    suspend fun getCaregivers() {
        val response = RetrofitClient.apiService.getCaregivers()
        if (response.isSuccessful && response.body() != null) {
            val caregivers = response.body()!!
            listCaregivers = caregivers.toMutableList()
        } else {
            listCaregivers = arrayListOf()
        }
    }

    suspend fun getCaregiverId(i:Int){
        val response = RetrofitClient.apiService.getCaregiverId(i)
        if(response.body()!=null){
            caregiver = response.body()!!
        }
    }

    suspend fun getCaregiverById(id: Long): Caregiver? {
        return apiService.getCaregiverId(id.toInt()).body()
    }


    suspend fun getCaregiverSchedule(id: Int): List<Schedule>? {
        val response = apiService.getScheduleId(id)
        return if (response.isSuccessful) response.body() else null
    }

    var listCaregivers: MutableList<Caregiver> by mutableStateOf(arrayListOf())


}