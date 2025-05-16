package com.example.safechild.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.safechild.components.TopBar

@Composable
fun ServiceList(navController: NavController) {


    //var servicelist: MutableList<Service> by mutableStateOf(arrayListOf())

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(onOpenDrawer = {}) }
    ) {
            innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Service List",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold)

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(){

                    Card(
                        onClick = {},
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(5.dp)
                        ) {

                            Image(painter = painterResource(),
                                contentDescription = null,
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(50.dp)
                                    .padding(vertical = 30.dp))

                            Column(modifier = Modifier
                                .padding(horizontal = 5.dp)
                                .width(200.dp)
                            ) {
                                Text(text = "${it.fullName}")
                                Text(text = "${it.street}")
                                Text(text = "${it.rating}")
                                Text(text = "${it.district}")

                            }
                        }
                    }
                }

            }
        }



    }




}