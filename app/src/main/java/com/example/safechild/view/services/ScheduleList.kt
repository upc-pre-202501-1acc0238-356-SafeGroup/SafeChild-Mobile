package com.example.safechild.view.services

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.safechild.model.beans.schedules.Schedule
import com.example.safechild.viewmodel.appointments.AppointViewModel
import com.example.safechild.viewmodel.profiles.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun ScheduleList(appointmentViewModel: AppointViewModel, profileViewModel: ProfileViewModel, navController: NavHostController, context: Context) {

    val schedules by appointmentViewModel.schedulesList.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var availableDate by remember { mutableStateOf("") }
    val CustomColor = Color(14, 165, 170, 255)

    LaunchedEffect(Unit) {
        val caregiverId = profileViewModel.getCurrentUserId(context)
        appointmentViewModel.viewModelScope.launch(Dispatchers.IO) {
            appointmentViewModel.getSchedulesByCaregiverId(caregiverId)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = CustomColor.copy(alpha = 0.04f),
        topBar = {
            Surface(
                tonalElevation = 4.dp,
                shadowElevation = 2.dp,
                color = CustomColor.copy(alpha = 0.12f)
            ) {
                Text(
                    text = "Días Disponibles",
                    style = MaterialTheme.typography.headlineMedium,
                    color = CustomColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp, horizontal = 16.dp)
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                items(schedules) { schedule ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable {
                                navController.navigate("scheduleDetails/${schedule.id}")
                            },
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                        ),
                        elevation = CardDefaults.cardElevation(18.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = "Fecha disponible: ${schedule.availableDate}",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = CustomColor
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Turnos: ${schedule.scheduleShifts.size}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = CustomColor.copy(alpha = 0.7f)
                                )
                            }
                            IconButton(onClick = {
                                val caregiverId = profileViewModel.getCurrentUserId(context)
                                appointmentViewModel.viewModelScope.launch(Dispatchers.IO) {
                                    appointmentViewModel.getSchedulesByCaregiverId(caregiverId)
                                }
                                appointmentViewModel.deleteSchedule(schedule.id, caregiverId)
                            }) {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = "Eliminar",
                                    tint = Color(220, 38, 38, 255) // Rojo para eliminar
                                )
                            }
                        }
                    }
                    Divider(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        color = CustomColor.copy(alpha = 0.2f)
                    )
                }
            }

            FilledTonalButton(
                onClick = { showDialog = true },
                modifier = Modifier
                    .padding(16.dp)
                    .height(50.dp)
                    .width(250.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = CustomColor,
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Agregar día",
                    modifier = Modifier.size(20.dp),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Agregar día",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = {
                        Text(
                            "Nuevo día disponible",
                            style = MaterialTheme.typography.titleLarge,
                            color = CustomColor
                        )
                    },
                    text = {
                        Column {
                            Text(
                                "Ingrese la fecha disponible:",
                                style = MaterialTheme.typography.bodyMedium,
                                color = CustomColor
                            )
                            TextField(
                                value = availableDate,
                                onValueChange = { availableDate = it },
                                placeholder = {
                                    Text(
                                        "YYYY-MM-DD",
                                        color = CustomColor.copy(alpha = 0.5f)
                                    )
                                }
                            )
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                val caregiverId = profileViewModel.getCurrentUserId(context)
                                appointmentViewModel.postSchedule(
                                    caregiverId = caregiverId,
                                    availableDate = availableDate
                                )
                                appointmentViewModel.viewModelScope.launch(Dispatchers.IO) {
                                    appointmentViewModel.getSchedulesByCaregiverId(caregiverId)
                                }
                                showDialog = false
                                availableDate = ""
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = CustomColor,
                                contentColor = Color.White
                            )
                        ) {
                            Text("Agregar")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { showDialog = false },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = CustomColor.copy(alpha = 0.5f),
                                contentColor = Color.White
                            )
                        ) {
                            Text("Cancelar")
                        }
                    }
                )
            }
        }
    }
}