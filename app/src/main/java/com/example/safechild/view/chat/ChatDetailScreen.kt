package com.example.safechild.view

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.border
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavHostController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.platform.LocalContext
import com.example.safechild.model.beans.messaging.Message
import com.example.safechild.model.beans.messaging.Person
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.safechild.model.client.RetrofitClient

data class MessageItem(val sender: String, val message: String)

@Composable
fun ChatDetailScreen(navController: NavHostController, chatName: String, senderId: Long, receiverId: Long) {
    var message by remember { mutableStateOf(TextFieldValue()) }
    var messages by remember { mutableStateOf(listOf<Message>()) }

    val context = LocalContext.current

    // Llama a la API una vez
    LaunchedEffect(Unit) {
        RetrofitClient.messagingApiService.getMessages(senderId, receiverId).enqueue(object : Callback<List<Message>> {
            override fun onResponse(call: Call<List<Message>>, response: Response<List<Message>>) {
                if (response.isSuccessful) {
                    messages = response.body() ?: emptyList()
                } else {
                    Toast.makeText(context, "Error al obtener mensajes", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Message>>, t: Throwable) {
                Toast.makeText(context, "Fallo de red: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Text(
                text = "Conversación con $chatName",
                fontSize = 20.sp,
                modifier = Modifier.padding(16.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight(0.8f)
                    .padding(horizontal = 16.dp)
            ) {
                messages.forEach { msg ->
                    MessageItemView(
                        sender = msg.sender.username,
                        message = msg.content
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = message,
                    onValueChange = { message = it },
                    textStyle = TextStyle(color = Color.Black),
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                        .height(60.dp)
                        .border(1.dp, Color.Gray)
                        .padding(16.dp)
                )

                IconButton(onClick = {
                    if (message.text.isNotEmpty()) {
                        val newMessage = Message(
                            id = -1,
                            sender = Person(senderId, "Yo"),
                            receiver = Person(receiverId, chatName),
                            content = message.text,
                            timestamp = "ahora"
                        )
                        messages = messages + newMessage
                        message = TextFieldValue()
                    }
                }) {
                    Icon(imageVector = Icons.Filled.Send, contentDescription = "Enviar mensaje")
                }
            }

            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Volver a la lista de chats")
            }
        }
    }
}

@Composable
fun MessageItemView(sender: String, message: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text(
            text = sender,
            color = Color.Gray,
            fontSize = 14.sp
        )
        Text(
            text = message,
            fontSize = 16.sp
        )
    }
}