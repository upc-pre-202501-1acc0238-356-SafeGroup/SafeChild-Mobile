package com.example.safechild.views.nav

    import Payments
    import TopBar

    import android.content.Context
    import androidx.compose.foundation.layout.padding
    import androidx.compose.material3.Scaffold
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Modifier
    import androidx.navigation.compose.NavHost
    import androidx.navigation.compose.composable
    import androidx.navigation.compose.rememberNavController
    import com.example.safechild.viewmodel.PaymentMethodViewModel
    import com.example.safechild.views.View1

    @Composable
    fun Navigator(
        context: Context,
        paymentMethodViewModel: PaymentMethodViewModel,
    ) {
        val recordarPantalla = rememberNavController()
        Scaffold(
            topBar = {
                TopBar(
                    onOpenDrawer = {}
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = recordarPantalla,
                startDestination = "P1",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("P1") { View1(recordarPantalla) }
                composable("Payments") { Payments(paymentMethodViewModel, recordarPantalla, context) }
            }
        }
    }