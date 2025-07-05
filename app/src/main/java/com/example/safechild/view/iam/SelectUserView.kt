package com.example.safechild.view.iam

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.safechild.R

@Composable
fun UserTypeSelectionScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0EA5AA)) // Fondo personalizado
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo en la parte superior
        Image(
            painter = painterResource(id = R.drawable.safechildlogo), // Reemplaza con el ID de tu logo
            contentDescription = "Logo de la app",
            modifier = Modifier
                .fillMaxWidth(0.6f) // Ajusta el tamaño al 60% del ancho de la pantalla
                .aspectRatio(1f) // Mantiene la proporción del logo
                .padding(bottom = 32.dp) // Espaciado debajo del logo
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Conectando a los padres con cuidadores",
                fontSize = 20.sp,
                //color = Color(0xFF0EA5AA)
            )

            Spacer(modifier = Modifier.height(16.dp))


            Button(
                onClick = { navController.navigate("P1") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFC5DFE9),
                    contentColor = Color.Black
                )
            ) {
                Text(text = "Soy Cuidador")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFC5DFE9),
                    contentColor = Color.Black
                )
            ) {
                Text(text = "Soy Padre")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Selecciona que usuario eres",
                fontSize = 15.sp,
                //color = Color(0xFF0EA5AA)
            )
        }

    }
}