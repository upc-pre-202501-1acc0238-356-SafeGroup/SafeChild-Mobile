package com.example.safechild

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.safechild.network.RetrofitClient
import com.example.safechild.network.Message
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.safechild.nav.Navigator

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Navigator()
        }

        getMessagesFromBackend(3)
    }

    private fun getMessagesFromBackend(chatId: Long) {
        RetrofitClient.apiService.getChats(chatId).enqueue(object : Callback<List<Message>> {
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
