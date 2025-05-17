package com.example.safechild.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.safechild.components.TopBar
import com.example.safechild.network.Caregiver
import com.example.safechild.viewmodel.ServViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun ServiceDetails(id: Long, viewModel: ServViewModel, navController: NavController) {
    var caregiver by mutableStateOf<Caregiver?>(null)

    // Obtener los detalles del Caregiver usando el id
    androidx.compose.runtime.LaunchedEffect(id) {
        caregiver = viewModel.getCaregiverById(id)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(onOpenDrawer = {}) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Service Details",
                fontSize = 28.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            caregiver?.let {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    // Imagen de perfil
                    /*Image(
                        painter = painterResource(), // Reemplaza con tu recurso
                        contentDescription = "Imagen del cuidador",
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 16.dp)
                    )*/

                    // Nombre
                    Text(
                        text = "Nombre: ${it.completeName}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Dirección
                    Text(
                        text = "Dirección: ${it.address}",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Experiencia
                    Text(
                        text = "Experiencia: ${it.caregiverExperience} años",
                        fontSize = 16.sp,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Servicios completados
                    Text(
                        text = "Servicios completados: ${it.completedServices}",
                        fontSize = 16.sp,
                        color = Color.Green,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Biografía
                    Text(
                        text = "Biografía:",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Text(
                        text = it.biography,
                        fontSize = 16.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Tarifa por hora
                    Text(
                        text = "Tarifa por hora: ${it.farePerHour} USD",
                        fontSize = 16.sp,
                        color = Color.Blue,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Distritos
                    Text(
                        text = "Distritos: ${it.districtsScope}",
                        fontSize = 16.sp,
                        color = Color.Magenta,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    /*// Botón de acción
                    OutlinedButton(
                        onClick = { *//* Acción al presionar *//* },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 16.dp)
                    ) {
                        Text(
                            text = "Contactar",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }*/
                }
            } ?: Text(
                text = "Cargando detalles...",
                fontSize = 18.sp,
                color = Color.Red,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

