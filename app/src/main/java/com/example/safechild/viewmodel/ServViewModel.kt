package com.example.safechild.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safechild.network.entities.Caregiver
import com.example.safechild.network.entities.iam.SignUpRequest
import com.example.safechild.network.retrofit.RetrofitClient
import kotlinx.coroutines.launch

class ServViewModel : ViewModel() {
    var caregiverId by mutableStateOf<Long?>(null)
    var caregiver by mutableStateOf<Caregiver?>(null)
    var loading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    // Lista de cuidadores
    var caregivers by mutableStateOf<List<Caregiver>>(emptyList())
        private set

    // Cargar lista de cuidadores
    fun loadCaregivers() {
        loading = true
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getCaregivers()
                if (response.isSuccessful) {
                    caregivers = response.body() ?: emptyList()
                }
            } catch (_: Exception) { }
            loading = false
        }
    }

    // Registro de usuario (sign up)
    fun signUpCaregiver(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        loading = true
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.signUp(
                    SignUpRequest(username = email, password = password, roles = listOf("CAREGIVER"))
                )
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    onError("Error en el registro")
                }
            } catch (e: Exception) {
                onError(e.message ?: "Error desconocido")
            } finally {
                loading = false
            }
        }
    }

    // Crear perfil de caregiver
    fun createCaregiverProfile(caregiver: Caregiver, onSuccess: (Long) -> Unit, onError: (String) -> Unit) {
        loading = true
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.createCaregiver(caregiver)
                if (response.isSuccessful && response.body() != null) {
                    caregiverId = response.body()!!.id
                    onSuccess(caregiverId!!)
                } else {
                    onError("Error al crear perfil")
                }
            } catch (e: Exception) {
                onError(e.message ?: "Error desconocido")
            } finally {
                loading = false
            }
        }
    }

    // Obtener caregiver por id
    fun getCaregiverById(id: Long, onLoaded: (Caregiver?) -> Unit) {
        loading = true
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getCaregiverId(id.toInt())
                if (response.isSuccessful) {
                    caregiver = response.body()
                    onLoaded(caregiver)
                } else {
                    onLoaded(null)
                }
            } catch (e: Exception) {
                onLoaded(null)
            } finally {
                loading = false
            }
        }
    }

    // Actualizar perfil
    fun updateCaregiverProfile(id: Long, caregiver: Caregiver, onSuccess: () -> Unit, onError: (String) -> Unit) {
        loading = true
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.updateCaregiver(id, caregiver)
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    onError("Error al actualizar perfil")
                }
            } catch (e: Exception) {
                onError(e.message ?: "Error desconocido")
            } finally {
                loading = false
            }
        }
    }
}