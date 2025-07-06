package com.example.safechild.view.payments

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.safechild.model.beans.payments.Payment
import com.example.safechild.viewmodel.profiles.ProfileViewModel
import com.example.safechild.viewmodel.payments.PaymentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun PaymentsView(paymentViewModel: PaymentViewModel, profileViewModel: ProfileViewModel, recordarPantalla: NavHostController, context: Context) {
    val payments = paymentViewModel.paymentsList

    LaunchedEffect(Unit) {
        val caregiverId = profileViewModel.getCurrentUserId(context);
        paymentViewModel.viewModelScope.launch(Dispatchers.IO) {
            paymentViewModel.getPaymentsByCaregiverIdAndPaymentStatus(caregiverId, "SUCCEEDED")
        }
    }

    val succeededPayments = payments.filter { it.paymentStatus.uppercase() == "SUCCEEDED" }
    val otherPayments = payments.filter { it.paymentStatus.uppercase() != "SUCCEEDED" }

    val totalReales = succeededPayments.sumOf { it.amount } / 100.0
    val totalPotenciales = payments.sumOf { it.amount } / 100.0
    var selectedPayment by remember { mutableStateOf<Payment?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFD0F8CE))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Ganancias Recibidas", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32))
                    Text("$${"%.2f".format(totalReales)}", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.width(5.dp))

            Card(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .weight(1f),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Ganancias Potenciales", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFFF57C00))
                    Text("$${"%.2f".format(totalPotenciales)}", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
        if (succeededPayments.isNotEmpty()) {
            Text(
                "Pagos Completados",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1B5E20),
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        ) {
            items(succeededPayments) { payment ->
                PaymentCard(payment){ selectedPayment = payment}
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (otherPayments.isNotEmpty()) {
            Text(
                "Pagos Pendientes u Otros",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFD32F2F),
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            items(otherPayments) { payment ->
                PaymentCard(payment) {selectedPayment = payment}
            }
        }

        selectedPayment?.let { payment ->
            AlertDialog(
                onDismissRequest = { selectedPayment = null },
                title = { Text("Detalle de la Reserva") },
                text = {
                    Column {
                        Text("ID Pago: ${payment.id}")
                        Text("Monto: $${"%.2f".format(payment.amount / 100.0)}")
                        Text("Moneda: ${payment.currency}")
                        Text("Estado: ${payment.paymentStatus}")
                        Text("ID Reserva: ${payment.reservation}")
                        Text("ID Cuidador: ${payment.caregiverId}")
                        Text("ID Tutor: ${payment.tutorId}")
                        Text("Stripe ID: ${payment.stripePaymentId}")
                    }
                },
                confirmButton = {
                    Button(onClick = { selectedPayment = null }) {
                        Text("Cerrar")
                    }
                }
            )
        }


    }
}

@Composable
fun PaymentCard(payment: Payment, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 2.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = if (payment.paymentStatus.uppercase() == "SUCCEEDED") Color(0xFFE8F5E9) else Color(0xFFFFEBEE)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(vertical = 14.dp, horizontal = 18.dp)) {
            Text(
                "ID: ${payment.id}",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color(0xFF37474F)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "Monto: $${"%.2f".format(payment.amount / 100.0)}",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF388E3C).takeIf { payment.paymentStatus.uppercase() == "SUCCEEDED" } ?: Color(0xFFD84315)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Row {
                Text(
                    "Moneda: ${payment.currency}",
                    fontSize = 13.sp,
                    color = Color(0xFF616161),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    "Reserva: ${payment.reservation}",
                    fontSize = 13.sp,
                    color = Color(0xFF616161),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

