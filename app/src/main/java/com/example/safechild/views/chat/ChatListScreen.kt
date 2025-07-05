package com.example.safechild.views

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.safechild.model.beans.messaging.Message
import com.example.safechild.model.client.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class ChatPreview(val receiverId: Long, val name: String)

@Composable
fun ChatListScreen(navController: NavHostController) {
    var chats by remember { mutableStateOf<List<ChatPreview>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    val userId = 1L // usuario actual

    LaunchedEffect(Unit) {
        RetrofitClient.messagingApiService.getChats(userId).enqueue(object : Callback<List<Message>> {
            override fun onResponse(call: Call<List<Message>>, response: Response<List<Message>>) {
                if (response.isSuccessful) {
                    val messages = response.body() ?: emptyList()

                    val chatPreviews = messages.map {
                        ChatPreview(
                            receiverId = it.receiver.id,
                            name = it.receiver.username
                        )
                    }.distinctBy { it.receiverId }

                    chats = chatPreviews
                } else {
                    Log.e("ChatListScreen", "Error: ${response.code()}")
                }
                isLoading = false
            }

            override fun onFailure(call: Call<List<Message>>, t: Throwable) {
                Log.e("ChatListScreen", "Error cargando chats", t)
                isLoading = false
            }
        })
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {  }
    ) { innerPadding ->
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                items(chats) { chat ->
                    ChatListItem(
                        chatName = chat.name,
                        onClick = {
                            navController.navigate("chatDetails/${userId}/${chat.receiverId}/${chat.name}")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ChatListItem(chatName: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = chatName,
                fontSize = 18.sp,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Abrir chat"
            )
        }
    }
}
