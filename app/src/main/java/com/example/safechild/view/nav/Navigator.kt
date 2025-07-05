package com.example.safechild.view.nav

import Payments
import TopBar
import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.safechild.viewmodel.payments.PaymentMethodViewModel
import com.example.safechild.viewmodel.ServViewModel
import com.example.safechild.view.ChatDetailScreen
import com.example.safechild.view.ChatListScreen
import com.example.safechild.view.home.View1
import com.example.safechild.view.iam.UserTypeSelectionScreen
import com.example.safechild.view.iam.login.LoginScreen
import com.example.safechild.view.iam.signup.SignUpCaregiverScreen
import com.example.safechild.view.iam.signup.CaregiverProfileSetupScreen
import com.example.safechild.view.iam.profile.CaregiverProfileScreen
import com.example.safechild.view.services.ServiceDetails
import com.example.safechild.view.services.ServiceList

@Composable
fun Navigator(
    navController: NavHostController = rememberNavController(),
    context: Context,
    paymentMethodViewModel: PaymentMethodViewModel,
    servViewModel: ServViewModel = viewModel()
) {
    val currentRoute = navController.currentBackStackEntryFlow.collectAsState(initial = null).value?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute != "userTypeSelection" && currentRoute != "login" && currentRoute != null) {
                TopBar(
                    onOpenDrawer = {},
                    navController = navController,
                    servViewModel = servViewModel
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("userTypeSelection") {
                UserTypeSelectionScreen(navController = navController)
            }
            composable("login") {
                LoginScreen(navController = navController)
            }
            composable("caregiverSignUp") {
                SignUpCaregiverScreen(navController = navController, servViewModel = servViewModel)
            }
            composable(
                "caregiverProfileSetup/{email}",
                arguments = listOf(navArgument("email") { defaultValue = "" })
            ) { backStackEntry ->
                val email = backStackEntry.arguments?.getString("email") ?: ""
                CaregiverProfileSetupScreen(navController = navController, email = email, servViewModel = servViewModel)
            }
            composable(
                "caregiverProfile/{caregiverId}",
                arguments = listOf(navArgument("caregiverId") { defaultValue = "0" })
            ) { backStackEntry ->
                val caregiverId = backStackEntry.arguments?.getString("caregiverId")?.toLongOrNull() ?: 0L
                CaregiverProfileScreen(navController = navController, caregiverId = caregiverId, servViewModel = servViewModel)
            }
            composable("P1") {
                View1(nav = navController)
            }
            composable("Payments") {
                Payments(paymentMethodViewModel, navController, context)
            }
            composable("chatList") {
                ChatListScreen(navController = navController)
            }
            composable(
                "chatDetails/{senderId}/{receiverId}/{chatName}",
                arguments = listOf(
                    navArgument("senderId") { defaultValue = "0" },
                    navArgument("receiverId") { defaultValue = "0" },
                    navArgument("chatName") { defaultValue = "" }
                )
            ) { backStackEntry ->
                val senderId = backStackEntry.arguments?.getString("senderId")?.toLong() ?: 0L
                val receiverId = backStackEntry.arguments?.getString("receiverId")?.toLong() ?: 0L
                val chatName = backStackEntry.arguments?.getString("chatName") ?: ""
                ChatDetailScreen(navController = navController, senderId = senderId, receiverId = receiverId, chatName = chatName)
            }
            composable("caregiverRegistration") {
                // Si tienes una pantalla de registro adicional, agrégala aquí
            }
            composable("serviceList") {
                ServiceList(servViewModel, navController = navController)
            }
            composable(
                "serviceDetails/{id}",
                arguments = listOf(navArgument("id") { defaultValue = "0" })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")?.toLong() ?: 0L
                ServiceDetails(id = id, viewModel = servViewModel, navController = navController)
            }
        }
    }
}