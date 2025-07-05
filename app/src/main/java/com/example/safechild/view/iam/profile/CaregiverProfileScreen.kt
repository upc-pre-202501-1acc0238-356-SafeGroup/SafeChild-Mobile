package com.example.safechild.view.iam.profile

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
import com.example.safechild.view.iam.signup.districts
import com.example.safechild.viewmodel.ServViewModel

@Composable
fun CaregiverProfileScreen(navController: NavHostController, caregiverId: Long, servViewModel: ServViewModel) {
    val context = LocalContext.current
    var loaded by remember { mutableStateOf(false) }

    if (!loaded) {
        LaunchedEffect(Unit) {
            servViewModel.getCaregiverById(caregiverId) { loaded = true }
        }
    }

    servViewModel.caregiver?.let { c ->
        var completeName by remember { mutableStateOf(c.completeName) }
        var age by remember { mutableStateOf(c.age.toString()) }
        var address by remember { mutableStateOf(c.address) }
        var caregiverExperience by remember { mutableStateOf(c.caregiverExperience.toString()) }
        var biography by remember { mutableStateOf(c.biography) }
        var profileImage by remember { mutableStateOf(c.profileImage) }
        var farePerHour by remember { mutableStateOf(c.farePerHour.toString()) }
        var selectedDistrict by remember { mutableStateOf(c.districtsScope) }
        var expanded by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Editar Perfil", fontSize = 24.sp)
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
            TextField(
                value = biography,
                onValueChange = { biography = it },
                label = { Text("Biografía") },
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = profileImage,
                onValueChange = { profileImage = it },
                label = { Text("URL Imagen de Perfil") },
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = farePerHour,
                onValueChange = { farePerHour = it },
                label = { Text("Tarifa por hora") },
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Spacer(modifier = Modifier.height(8.dp))
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
                    val updatedCaregiver = c.copy(
                        completeName = completeName,
                        age = age.toIntOrNull() ?: 0,
                        address = address,
                        caregiverExperience = caregiverExperience.toIntOrNull() ?: 0,
                        biography = biography,
                        profileImage = profileImage,
                        farePerHour = farePerHour.toIntOrNull() ?: 0,
                        districtsScope = selectedDistrict
                    )
                    servViewModel.updateCaregiverProfile(
                        caregiverId,
                        updatedCaregiver,
                        onSuccess = {
                            Toast.makeText(context, "Perfil actualizado", Toast.LENGTH_SHORT).show()
                        },
                        onError = { msg ->
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        }
                    )
                },
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("Guardar cambios")
            }
        }
    }
}