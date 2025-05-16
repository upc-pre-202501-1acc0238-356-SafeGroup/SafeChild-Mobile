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
import com.example.safechild.network.RetrofitClient
import com.example.safechild.network.entities.Caregiver
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            RetrofitClient.apiService.getCaregivers().enqueue(object : Callback<List<Caregiver>> {
                override fun onResponse(call: Call<List<Caregiver>>, response: Response<List<Caregiver>>) {
                    if (response.isSuccessful) {
                        val caregivers = response.body() ?: emptyList()
                        val user = caregivers.find { it.email == email } // Simulación básica

                        if (user != null) {
                            Toast.makeText(context, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                            navController.navigate("chatList") {
                                popUpTo("userTypeSelection") { inclusive = true }
                            }
                        } else {
                            Toast.makeText(context, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "Error al obtener datos", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<Caregiver>>, t: Throwable) {
                    Toast.makeText(context, "Fallo de red: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }) {
            Text(text = "Iniciar Sesión")
        }
    }
}