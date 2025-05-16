package com.example.safechild.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.safechild.components.TopBar

@Composable
fun ServiceDetails(navController: NavController) {


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

            Text(text = "Service Details",
                fontSize = 25.sp,
                color = Color.Blue,
                fontWeight = FontWeight.Bold)


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
                    .padding(5.dp)
            ) {

                //Image(painter = painterResource(),
                //    contentDescription = null,
                //    modifier = Modifier
                //        .height(50.dp)
                //        .width(50.dp)
                //        .padding(vertical = 30.dp))

                Column(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .width(200.dp)
                ) {
                    Text(text = "${it.fullName}")
                    Text(text = "${it.street}")
                    Text(text = "${it.district}")
                    Text(text = "${it.date}")
                    Text(text = "${it.time}")
                    Text(text = "${it.price}")

                }
            }

            Text(
                text = "Details",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold)

            Text(
                text = "${it.description}",
                modifier = Modifier
                    .padding(20.dp)
                    .padding(vertical = 50.dp)

            )

            OutlinedButton(
                onClick = {}
            ) {
                Text(text = "Accept Service",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold)
            }

            OutlinedButton(
                onClick = {}
            ) {
                Text(text = "Decline Service",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold)
            }
        }
    }

}