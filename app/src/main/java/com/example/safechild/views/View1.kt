package com.example.safechild.views

import com.example.safechild.components.TopBar
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

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                onOpenDrawer = {}
            )
        },

    ){
            innerPadding->Principal(
        modifier=Modifier.padding(innerPadding),
        nav
    )
    }
}

@Composable
fun Principal(modifier: Modifier,nav: NavHostController){


    ElevatedButton(
        colors = ButtonDefaults.buttonColors(Color(6,105,191,255)),
        modifier = Modifier
            .padding(50.dp)
            .padding(vertical = 50.dp)
            .width(300.dp),

        onClick = { nav.navigate("P2") }
    ){
        Text(
            text = "View2",fontSize = 20.sp,
            color = Color.White,fontWeight = FontWeight.Bold
        )
    }
}