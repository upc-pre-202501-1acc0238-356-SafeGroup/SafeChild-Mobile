package com.example.safechild.views.iam.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.safechild.network.RetrofitClient
import com.example.safechild.network.entities.Caregiver
import com.example.safechild.views.iam.isValidEmail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CaregiverRegistrationScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var completeName by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var caregiverExperience by remember { mutableStateOf("") }
    var completedServices by remember { mutableStateOf("") }
    var biography by remember { mutableStateOf("") }
    var profileImage by remember { mutableStateOf("") }
    var farePerHour by remember { mutableStateOf("") }
    var districtsScope by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Registro de Cuidador", fontSize = 20.sp)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = completeName, onValueChange = { completeName = it }, label = { Text("Nombre Completo") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = age, onValueChange = { age = it }, label = { Text("Edad") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = address, onValueChange = { address = it }, label = { Text("Dirección") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = caregiverExperience, onValueChange = { caregiverExperience = it }, label = { Text("Experiencia como Cuidador (años)") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = completedServices, onValueChange = { completedServices = it }, label = { Text("Servicios Completados") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = biography, onValueChange = { biography = it }, label = { Text("Biografía") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = profileImage, onValueChange = { profileImage = it }, label = { Text("URL de Imagen de Perfil") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = farePerHour, onValueChange = { farePerHour = it }, label = { Text("Tarifa por Hora") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = districtsScope, onValueChange = { districtsScope = it }, label = { Text("Áreas de Cobertura") })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (!isValidEmail(email)) {
                Toast.makeText(context, "Formato de correo inválido", Toast.LENGTH_SHORT).show()
                return@Button
            }
            val caregiver = Caregiver(
                id = 0L, // El ID puede ser 0 o ignorado si el backend lo genera automáticamente
                email = email,
                completeName = completeName,
                age = age.toIntOrNull() ?: 0,
                address = address,
                caregiverExperience = caregiverExperience.toIntOrNull() ?: 0,
                completedServices = completedServices.toIntOrNull() ?: 0,
                biography = biography,
                profileImage = profileImage,
                farePerHour = farePerHour.toIntOrNull() ?: 0,
                districtsScope = districtsScope,
                profileId = 0L

            )

            RetrofitClient.apiService.createCaregiver(caregiver).enqueue(object : Callback<Caregiver> {
                override fun onResponse(call: Call<Caregiver>, response: Response<Caregiver>) {
                    if (response.isSuccessful) {
                        val registeredCaregiver = response.body()
                        Toast.makeText(context, "Registro exitoso: ${registeredCaregiver?.completeName}", Toast.LENGTH_SHORT).show()
                        navController.navigate("chatList")
                    } else {
                        val errorBody = response.errorBody()?.string() ?: "Sin detalles del error"
                        Toast.makeText(context, "Error en el registro: ${response.code()} - $errorBody", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<Caregiver>, t: Throwable) {
                    Toast.makeText(context, "Fallo de red: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }) {
            Text(text = "Registrar")
        }
    }
}