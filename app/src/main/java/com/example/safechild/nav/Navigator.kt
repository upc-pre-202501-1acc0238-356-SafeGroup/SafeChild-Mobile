package com.example.safechild.nav

import View2
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.safechild.views.View1

@Composable
fun Navigator(){
    val recordarPantalla = rememberNavController()
    NavHost(navController= recordarPantalla,
        startDestination = "P1"){
        composable("P1") { View1(recordarPantalla) }
        composable("P2") { View2(recordarPantalla) }
    }
}