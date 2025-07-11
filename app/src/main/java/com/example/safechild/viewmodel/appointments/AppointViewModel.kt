package com.example.safechild.viewmodel.appointments

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safechild.model.beans.schedules.Schedule
import com.example.safechild.model.client.RetrofitClient
import com.example.safechild.model.client.RetrofitClient.schedulesApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class AppointViewModel : ViewModel() {
    private val _schedulesList = MutableStateFlow<List<Schedule>>(emptyList())
    val schedulesList: StateFlow<List<Schedule>> = _schedulesList

    val isLoading = MutableStateFlow(false)

    suspend fun getSchedulesByCaregiverId(caregiverId: Long) {
        try {
            val response = RetrofitClient.schedulesApiService.getSchedulesByCaregiverId(caregiverId)
            if (response.isSuccessful) {
                response.body()?.let { schedules ->
                    _schedulesList.value = schedules
                }
            } else {
                println("HTTP Error: ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun postSchedule(caregiverId: Long, availableDate: String) {
        viewModelScope.launch {
            val schedule = Schedule(
                id = 0L, // o el valor que corresponda, si el backend lo ignora
                caregiverId = caregiverId,
                availableDate = availableDate,
                scheduleShifts = emptyList() // si es requerido
            )
            val response = schedulesApiService.createSchedule(schedule)
            if (response.isSuccessful) {
                getSchedulesByCaregiverId(caregiverId)
            } else {
                // Maneja el error
            }
        }
    }

    fun deleteSchedule(scheduleId: Long, caregiverId: Long) {
        viewModelScope.launch {
            try {
                val response = schedulesApiService.deleteSchedule(scheduleId)
                if (response.isSuccessful) {
                    getSchedulesByCaregiverId(caregiverId)
                    println("Horario eliminado correctamente.")
                } else {
                    println("Error al eliminar el horario: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                println("Excepción al eliminar el horario: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    fun updateShiftAvailability(shiftId: Long, available: Boolean) {
        viewModelScope.launch {
            try {
                // Solo envía el campo "available" como requiere el backend
                val body = mapOf("available" to available)
                val response = schedulesApiService.updateShiftAvailability(shiftId, body)
                if (response.isSuccessful) {
                    // Actualiza el shift localmente en el StateFlow
                    _schedulesList.value = _schedulesList.value.map { schedule ->
                        schedule.copy(
                            scheduleShifts = schedule.scheduleShifts.map { shift ->
                                if (shift.id == shiftId) shift.copy(available = available) else shift
                            }
                        )
                    }

                } else {
                    println("Error al actualizar disponibilidad: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}