package com.example.safechild.views.iam.login


import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.compose.foundation.clickable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration

import com.example.safechild.model.beans.iam.SignInRequest
import com.example.safechild.model.client.BaseRetrofitClient
import com.example.safechild.model.client.RetrofitClient

var globalToken: String? = null // Puedes mover esto a un ViewModel o DataStore



@Composable
fun LoginScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Iniciar Sesión", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = password, onValueChange = { password = it }, label = { Text("Contraseña") })
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val response = RetrofitClient.authApiService.signIn(
                        SignInRequest(username = email, password = password)
                    )
                    if (response.isSuccessful && response.body() != null) {
                        val signInResponse = response.body()!!
                        globalToken = signInResponse.token
                        BaseRetrofitClient.updateToken(globalToken)// Guarda el token
                        Toast.makeText(context, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                        navController.navigate("P1") {
                            popUpTo("userTypeSelection") { inclusive = true }
                        }
                    } else {
                        Toast.makeText(context, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }) {
            Text(text = "Iniciar Sesión")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "¿Aún no tienes cuenta? Regístrate",
            color = Color(0xFF0EA5AA),
            fontSize = 16.sp,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable {
                navController.navigate("caregiverSignUp")
            }
        )
    }
}