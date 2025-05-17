package com.example.safechild.nav

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.safechild.viewmodel.ServViewModel
import com.example.safechild.views.ChatDetailScreen
import com.example.safechild.views.ChatListScreen
import com.example.safechild.views.ServiceDetails
import com.example.safechild.views.ServiceList

@Composable
fun Navigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "serviceList") {
        composable("chatList") {
            ChatListScreen(navController = navController)
        }

        composable("chatDetails/{senderId}/{receiverId}/{chatName}") { backStackEntry ->
            val senderId = backStackEntry.arguments?.getString("senderId")?.toLong() ?: 0L
            val receiverId = backStackEntry.arguments?.getString("receiverId")?.toLong() ?: 0L
            val chatName = backStackEntry.arguments?.getString("chatName") ?: ""
            ChatDetailScreen(navController = navController, senderId = senderId, receiverId = receiverId, chatName = chatName)
        }

        // Nueva ruta para la pantalla de ServiceList
        composable("serviceList") {
            val servViewModel: ServViewModel = viewModel()
            ServiceList(servViewModel ,navController = navController)
        }

        // Nueva ruta para la pantalla de ServiceList
        composable("serviceDetails/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toLong() ?: 0L
            val servViewModel: ServViewModel = viewModel()
            ServiceDetails(id = id, viewModel = servViewModel, navController = navController)
        }
    }
}