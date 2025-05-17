package com.example.safechild.views.services

import TopBar
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.safechild.network.entities.Caregiver
import com.example.safechild.network.entities.Schedule
import com.example.safechild.viewmodel.ServViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun ServiceList(viewModel: ServViewModel, navController: NavHostController) {
    var serviceList: MutableList<Caregiver> by mutableStateOf(arrayListOf())
    var schedules: MutableMap<Long, List<Schedule>?> by mutableStateOf(mutableMapOf())

    androidx.compose.runtime.LaunchedEffect(Unit) {
        viewModel.getCaregivers()
        serviceList = viewModel.listCaregivers

        // Cargar horarios para cada Caregiver
        serviceList.forEach { caregiver ->
            val schedule = viewModel.getCaregiverSchedule(caregiver.id.toInt())
            schedules[caregiver.id] = schedule
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Service List",
                fontSize = 28.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                items(serviceList) { caregiver ->
                    val schedule = schedules[caregiver.id]
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("ServiceDetails/${caregiver.id}")
                            },
                        elevation = CardDefaults.cardElevation(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(horizontal = 5.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = caregiver.completeName,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                                Text(
                                    text = caregiver.address,
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                                Text(
                                    text = "Servicios completados: ${caregiver.completedServices}",
                                    fontSize = 14.sp,
                                    color = Color.DarkGray
                                )
                                Text(
                                    text = "Distritos: ${caregiver.districtsScope}",
                                    fontSize = 14.sp,
                                    color = Color.Blue
                                )

                                // Mostrar horarios si están disponibles
                                schedule?.forEach {
                                    Text(
                                        text = "Día: ${it.weekDay}, Inicio: ${it.startHour}, Fin: ${it.endHour}",
                                        fontSize = 14.sp,
                                        color = Color.DarkGray
                                    )
                                }
                            }
                        }
                    }
                }
            }

            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .padding(16.dp)
                    .height(50.dp)
                    .width(150.dp),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Add Service",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            }
        }
    }
}