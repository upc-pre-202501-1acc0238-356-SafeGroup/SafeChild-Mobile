package com.example.safechild

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.safechild.model.beans.messaging.Message
import com.example.safechild.model.client.RetrofitClient
import com.example.safechild.model.storage.UserSessionManager
import com.example.safechild.view.iam.login.globalId
import com.example.safechild.view.iam.login.globalToken
import com.example.safechild.view.nav.Navigator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        globalId = UserSessionManager.getUserId(context)
        globalToken = UserSessionManager.getToken(context)

        setContent {
            val navController = rememberNavController()
            Navigator(
                navController = navController,
                context = context
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

