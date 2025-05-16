package com.example.safechild.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.safechild.views.ChatDetailScreen
import com.example.safechild.views.ChatListScreen
import com.example.safechild.views.iam.UserTypeSelectionScreen
import com.example.safechild.views.iam.login.LoginScreen
import com.example.safechild.views.iam.signup.CaregiverRegistrationScreen

@Composable
fun Navigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "userTypeSelection") {

        composable("userTypeSelection") {
            UserTypeSelectionScreen(navController = navController)
        }

        composable("login") {
            LoginScreen(navController = navController)
        }

        composable("chatList") {
            ChatListScreen(navController = navController)
        }

        // Ruta actualizada y correcta
        composable("chatDetails/{senderId}/{receiverId}/{chatName}") { backStackEntry ->
            val senderId = backStackEntry.arguments?.getString("senderId")?.toLong() ?: 0L
            val receiverId = backStackEntry.arguments?.getString("receiverId")?.toLong() ?: 0L
            val chatName = backStackEntry.arguments?.getString("chatName") ?: ""
            ChatDetailScreen(navController = navController, senderId = senderId, receiverId = receiverId, chatName = chatName)
        }

        composable("userTypeSelection") {
            UserTypeSelectionScreen(navController = navController)
        }
        composable("caregiverRegistration") {
            CaregiverRegistrationScreen(navController = navController)
        }
        composable("caregiverRegistration") {
            CaregiverRegistrationScreen(navController = navController)
        }
    }
}
