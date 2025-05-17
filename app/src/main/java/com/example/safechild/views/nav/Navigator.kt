package com.example.safechild.views.nav

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
    import com.example.safechild.viewmodel.PaymentMethodViewModel
    import com.example.safechild.viewmodel.ServViewModel
    import com.example.safechild.views.ChatDetailScreen
    import com.example.safechild.views.ChatListScreen
    import com.example.safechild.views.View1
    import com.example.safechild.views.iam.UserTypeSelectionScreen
    import com.example.safechild.views.iam.login.LoginScreen
    import com.example.safechild.views.iam.signup.CaregiverRegistrationScreen
    import com.example.safechild.views.services.ServiceDetails
    import com.example.safechild.views.services.ServiceList

@Composable
fun Navigator(
    navController: NavHostController,
    context: Context,
    paymentMethodViewModel: PaymentMethodViewModel,
) {
    val currentRoute = navController.currentBackStackEntryFlow.collectAsState(initial = null).value?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute != "userTypeSelection" && currentRoute != null) { // Excluir TopBar en UserTypeSelectionScreen
                TopBar(
                    onOpenDrawer = {},
                    navController = navController
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
            composable("P1") { View1(navController) }
            composable("Payments") { Payments(paymentMethodViewModel, navController, context) }
            composable("login") {
                LoginScreen(navController = navController)
            }
            composable("chatList") {
                ChatListScreen(navController = navController)
            }
            composable("chatDetails/{senderId}/{receiverId}/{chatName}") { backStackEntry ->
                val senderId = backStackEntry.arguments?.getString("senderId")?.toLong() ?: 0L
                val receiverId = backStackEntry.arguments?.getString("receiverId")?.toLong() ?: 0L
                val chatName = backStackEntry.arguments?.getString("chatName") ?: ""
                ChatDetailScreen(navController = navController, senderId = senderId, receiverId = receiverId, chatName = chatName)
            }
            composable("caregiverRegistration") {
                CaregiverRegistrationScreen(navController = navController)
            }

            composable("serviceList") {
                val servViewModel: ServViewModel = viewModel()
                ServiceList(servViewModel ,navController = navController)
            }

            composable("serviceDetails/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")?.toLong() ?: 0L
                val servViewModel: ServViewModel = viewModel()
                ServiceDetails(id = id, viewModel = servViewModel, navController = navController)
            }
        }
    }
}