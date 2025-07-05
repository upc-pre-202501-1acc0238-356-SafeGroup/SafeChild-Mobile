package com.example.safechild

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.safechild.model.beans.messaging.Message
import com.example.safechild.model.client.RetrofitClient
import com.example.safechild.viewmodel.PaymentMethodViewModel
import com.example.safechild.views.nav.Navigator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    val viewModel by viewModels<PaymentMethodViewModel>()
    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            Navigator(
                navController = navController,
                context = context,
                paymentMethodViewModel = viewModel

            )
        }
        getMessagesFromBackend(3)
    }
    private fun getMessagesFromBackend(chatId: Long) {
        RetrofitClient.messagingApiService.getChats(chatId).enqueue(object : Callback<List<Message>> {
            override fun onResponse(call: Call<List<Message>>, response: Response<List<Message>>) {
                if (response.isSuccessful) {
                    val messages = response.body()

                    Log.d("Retrofit", "Mensajes: $messages")
                } else {
                    Log.e("Retrofit", "Error: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<List<Message>>, t: Throwable) {
                Log.e("Retrofit", "Fallo la conexión: ${t.message}")
            }
        })
    }

}

