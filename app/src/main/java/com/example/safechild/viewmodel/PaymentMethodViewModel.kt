package com.example.safechild.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.safechild.network.room.model.beans.PaymentMethod
import com.example.safechild.network.retrofit.RetrofitClient
import com.example.safechild.network.room.model.db.AppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PaymentMethodViewModel: ViewModel() {
    var paymentMethod: PaymentMethod = PaymentMethod( 1, "","", "", "", "",)

    suspend fun getPaymentMethod(id: Int){
        val response = RetrofitClient.webService.getPaymentMethodId(id)
        if (response.body()!=null){
            paymentMethod = response.body()!!
        }
    }

    var listaPaymentMethod: MutableList<PaymentMethod> by mutableStateOf(arrayListOf())

    fun listarPaymentMethods(context: Context){
        var appDB: AppDataBase = AppDataBase.getDatabase(context)
        GlobalScope.launch {

            listaPaymentMethod=appDB.paymentMethodDao().ListaPaymentMethods().toMutableList()
        }
    }

    fun insertPaymentMethod(paymentMethod: PaymentMethod, context: Context){
        var appDB: AppDataBase = AppDataBase.getDatabase(context)
        GlobalScope.launch(Dispatchers.IO) {
            appDB.paymentMethodDao().insert(paymentMethod)
        }
    }

    fun updatePaymentMethod(paymentMethod: PaymentMethod, context: Context){
        var appDB: AppDataBase = AppDataBase.getDatabase(context)
        GlobalScope.launch(Dispatchers.IO) {
            appDB.paymentMethodDao().update(paymentMethod)
        }
    }

    fun deletePaymentMethod(paymentMethod: PaymentMethod, context: Context, onSuccess: () -> Unit = {}){
        var appDB: AppDataBase = AppDataBase.getDatabase(context)
        GlobalScope.launch(Dispatchers.IO) {
            appDB.paymentMethodDao().delete(paymentMethod)
            onSuccess()
        }
    }
}

