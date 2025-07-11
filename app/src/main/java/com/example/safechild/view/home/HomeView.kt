package com.example.safechild.view.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun View1(nav:NavHostController){
//
//    Scaffold (
//        modifier = Modifier.fillMaxSize(),
//
//
//    ){
//            innerPadding->Principal(
//        modifier=Modifier.padding(innerPadding),
//        nav
//    )
//    }
    Principal(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        nav = nav
    )
}

@Composable
fun Principal(modifier: Modifier,nav: NavHostController){


    Column(modifier = modifier){
        ElevatedButton(
            colors = ButtonDefaults.buttonColors(Color(6,105,191,255)),
            modifier = Modifier
                .padding(50.dp)
                .width(300.dp),

            onClick = { nav.navigate("Payments") }
        ){
            Text(
                text = "Pagos",fontSize = 20.sp,
                color = Color.White,fontWeight = FontWeight.Bold
            )
        }
        ElevatedButton(
            colors = ButtonDefaults.buttonColors(Color(6,105,191,255)),
            modifier = Modifier
                .padding(50.dp)
                .width(300.dp),

            onClick = { nav.navigate("chatList") }
        ){
            Text(
                text = "Chats",fontSize = 20.sp,
                color = Color.White,fontWeight = FontWeight.Bold
            )
        }
        ElevatedButton(
            colors = ButtonDefaults.buttonColors(Color(6,105,191,255)),
            modifier = Modifier
                .padding(50.dp)
                .width(300.dp),

            onClick = { nav.navigate("scheduleList") }
        ){
            Text(
                text = "Horarios",fontSize = 20.sp,
                color = Color.White,fontWeight = FontWeight.Bold
            )
        }

        ElevatedButton(
            colors = ButtonDefaults.buttonColors(Color(6,105,191,255)),
            modifier = Modifier
                .padding(50.dp)
                .width(300.dp),

            onClick = { nav.navigate("PaymentsView") }
        ){
            Text(
                text = "Payments",fontSize = 20.sp,
                color = Color.White,fontWeight = FontWeight.Bold
            )
        }


    }

}