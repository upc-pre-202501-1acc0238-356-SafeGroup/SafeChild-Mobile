package com.example.safechild.viewmodel.payments

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.safechild.model.beans.payments.Payment
import com.example.safechild.model.beans.payments.PaymentMethod
import com.example.safechild.model.client.RetrofitClient
import com.example.safechild.model.db.AppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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