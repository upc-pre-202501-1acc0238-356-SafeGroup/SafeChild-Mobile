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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.safechild.components.TopBar
import com.example.safechild.network.Caregiver
import com.example.safechild.viewmodel.ServViewModel

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable

fun ServiceList(viewModel: ServViewModel, navController: NavController) {
    var serviceList: MutableList<Caregiver> by mutableStateOf(arrayListOf())

    androidx.compose.runtime.LaunchedEffect(Unit) {
        viewModel.getCaregivers()
        serviceList = viewModel.listCaregivers
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
                items(serviceList) {
                    Card(
                        onClick = {
                            navController.navigate("ServiceDetails/${it.id}")
                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        elevation = 8.dp,
                        backgroundColor = Color(0xFFF5F5F5),
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
                                    text = it.completeName,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                                Text(
                                    text = it.address,
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                                Text(
                                    text = "Servicios completados: ${it.completedServices}",
                                    fontSize = 14.sp,
                                    color = Color.DarkGray
                                )
                                Text(
                                    text = "Distritos: ${it.districtsScope}",
                                    fontSize = 14.sp,
                                    color = Color.Blue
                                )
                            }
                        }
                    }
                }
            }

            // Botón fijo al final
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