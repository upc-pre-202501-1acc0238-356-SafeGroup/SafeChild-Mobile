package com.example.safechild.viewmodel.payments


import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.safechild.model.beans.payments.Payment
import com.example.safechild.model.client.RetrofitClient


class PaymentViewModel: ViewModel() {
    var paymentsList = mutableStateListOf<Payment>()
        private set

    var payment: Payment= Payment(0 , "", "", 0, 0, 0,0, "")

    suspend fun getPaymentsByCaregiverIdAndPaymentStatus(caregiverId: Long, paymentStatus: String) {
        try {
            val response = RetrofitClient.paymentApiService.getPaymentsByCaregiverIdAndPaymentStatus(caregiverId, paymentStatus)
            if (response.isSuccessful) {
                response.body()?.let { payments ->
                    paymentsList.clear()
                    paymentsList.addAll(payments)
                }
            } else {
                println("HTTP Error: ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}