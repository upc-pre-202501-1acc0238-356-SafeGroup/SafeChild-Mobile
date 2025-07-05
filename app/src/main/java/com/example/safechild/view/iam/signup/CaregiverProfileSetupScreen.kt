package com.example.safechild.view.iam.signup

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.safechild.model.beans.profiles.Caregiver
import com.example.safechild.viewmodel.ServViewModel

val districts = listOf(
    "ANCON", "ATE", "BARRANCO", "BELLAVISTA", "BRENA", "CARABAYLLO", "CHACLACAYO", "CHORRILLOS",
    "CIENEGUILLA", "COMAS", "EL_AGUSTINO", "INDEPENDENCIA", "JESUS_MARIA", "LA_MOLINA", "LA_PERLA",
    "LA_PUNTA", "LA_VICTORIA", "LIMA", "LINCE", "LOS_OLIVOS", "LURIN", "MAGDALENA_DEL_MAR",
    "MIRAFLORES", "PACHACAMAC", "PUCUSANA", "PUEBLO_LIBRE", "PUENTE_PIEDRA", "PUNTA_HERMOSA",
    "PUNTA_NEGRA", "RIMAC", "SAN_BARTOLO", "SAN_BORJA", "SAN_ISIDRO", "SAN_JUAN_DE_LURIGANCHO",
    "SAN_JUAN_DE_MIRAFLORES", "SAN_LUIS", "SAN_MARTIN_DE_PORRES", "SAN_MIGUEL", "SANTA_ANITA",
    "SANTA_MARIA_DEL_MAR", "SANTA_ROSA", "SANTIAGO_DE_SURCO", "SURQUILLO", "VENTANILLA",
    "VILLA_EL_SALVADOR", "VILLA_MARIA_DEL_TRIUNFO"
)

@Composable
fun CaregiverProfileSetupScreen(navController: NavHostController, email: String, servViewModel: ServViewModel) {
    var completeName by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var caregiverExperience by remember { mutableStateOf("") }
    var selectedDistrict by remember { mutableStateOf(districts.first()) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Completa tu perfil", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = completeName,
            onValueChange = { completeName = it },
            label = { Text("Nombre completo") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Edad") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Dirección") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = caregiverExperience,
            onValueChange = { caregiverExperience = it },
            label = { Text("Años de experiencia") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(8.dp))
        var expanded by remember { mutableStateOf(false) }
        Box {
            OutlinedButton(onClick = { expanded = true }, modifier = Modifier.fillMaxWidth(0.8f)) {
                Text(selectedDistrict)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                districts.forEach { district ->
                    DropdownMenuItem(
                        text = { Text(district) },
                        onClick = {
                            selectedDistrict = district
                            expanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val caregiver = Caregiver(
                    id = 0L,
                    completeName = completeName,
                    age = age.toIntOrNull() ?: 0,
                    address = address,
                    caregiverExperience = caregiverExperience.toIntOrNull() ?: 0,
                    completedServices = 0,
                    biography = "",
                    profileImage = "",
                    farePerHour = 0,
                    districtsScope = selectedDistrict,
                    profileId = 0L
                )
                servViewModel.createCaregiverProfile(
                    caregiver,
                    onSuccess = { id ->
                        Toast.makeText(context, "Perfil creado correctamente", Toast.LENGTH_SHORT).show()
                        navController.navigate("login")
                    },
                    onError = { msg ->
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    }
                )
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Guardar perfil")
        }
    }
}