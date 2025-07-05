import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.safechild.model.beans.payments.PaymentMethod
import com.example.safechild.viewmodel.payments.PaymentMethodViewModel

@Composable
fun Payments(
    paymentMethodViewModel: PaymentMethodViewModel,
    nav: NavHostController,
    context: Context
) {
    var showDialog by remember { mutableStateOf(false) }
    var isEditing by remember { mutableStateOf(false) }
    var pmInEdit by remember { mutableStateOf<PaymentMethod?>(null) }
    var pmInInfo by remember { mutableStateOf<PaymentMethod?>(null) }


    var showInfoDialog by remember { mutableStateOf(false) }

    var txtCardHolder by remember { mutableStateOf("") }
    var txtCardNumber by remember { mutableStateOf("") }
    var txtExpiration by remember { mutableStateOf("") }
    var txtCvv by remember { mutableStateOf("") }
    var txtCardType by remember { mutableStateOf("") }

    val SoftWhite = Color(0xFFF3F7F9)
    val InfoPrimary = Color(0xFF4DB7C5)
    val InfoSecondary = Color(0xFF7CD7E2)
    val ActionDark = Color(0xFF003A5A)
    val BorderLight = Color(0xFFA8DADC)
    val LightBackground = Color(0xFFD4F1F4)
    val EditBlue = Color(0xFF508CA4)

    paymentMethodViewModel.listarPaymentMethods(context)
    val listaPM = paymentMethodViewModel.listaPaymentMethod

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Métodos de Pago",
            fontSize = 22.sp,
            color = Color(14,165,170,255),
            fontWeight = FontWeight.Bold
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(listaPM) { method ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, Color.Black)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {

                        // Parte superior: número y tipo de tarjeta
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            val lastFour = method.cardNumber?.takeLast(4) ?: "****"
                            Text(
                                text = "**** **** **** $lastFour",
                                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                            )

                            Box(
                                modifier = Modifier
                                    .border(2.dp, Color.Black)
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = method.cardType?.lowercase()?.replaceFirstChar { it.uppercase() } ?: "Desconocido",
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        // Botones de acción
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            IconButton(
                                modifier = Modifier.size(24.dp)
                                    .padding(horizontal = 2.dp),
                                onClick = {
                                    pmInInfo = method
                                    showInfoDialog = true
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = "Información",
                                    tint = InfoPrimary
                                )
                            }

                            Spacer(modifier = Modifier.width(2.dp))


                            IconButton(
                                modifier = Modifier.size(24.dp)
                                    .padding(horizontal = 2.dp),
                                onClick = {
                                    isEditing = true
                                    pmInEdit = method

                                    txtCardHolder = method.cardHolderName ?: ""
                                    txtCardNumber = method.cardNumber ?: ""
                                    txtExpiration = method.expiryDate ?: ""
                                    txtCvv = method.cvv ?: ""
                                    txtCardType = method.cardType ?: ""

                                    showDialog = true
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Editar",
                                    tint = 	EditBlue
                                )
                            }

                            Spacer(modifier = Modifier.width(2.dp))


                            IconButton(
                                modifier = Modifier.size(24.dp)
                                    .padding(horizontal = 2.dp),
                                onClick = {
                                    paymentMethodViewModel.deletePaymentMethod(method, context) {
                                        paymentMethodViewModel.listarPaymentMethods(context)
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Eliminar",
                                    tint = ActionDark
                                )
                            }
                        }
                    }
                }
            }
        }



        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),

            colors = ButtonDefaults.buttonColors(containerColor = Color(14,165,170,255)),
            onClick = {
                isEditing = false
                pmInEdit = null

                txtCardHolder = ""
                txtCardNumber = ""
                txtExpiration = ""
                txtCvv = ""
                txtCardType = ""

                showDialog = true
            }
        ) {
            Text(text = if (isEditing) "Editar Método de Pago" else "Nuevo Método de Pago",)
        }
        if (showDialog) {

            AlertDialog(

                title = {
                    Text(
                        text = "Nuevo Método de Pago",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                onDismissRequest = { showDialog = false },

                confirmButton = {
                    TextButton(


                        onClick = {

                            if (txtCardHolder.isBlank() || txtCardNumber.isBlank() || txtExpiration.isBlank() || txtCvv.isBlank() || txtCardType.isBlank()) {
                                Toast.makeText(context, "Por favor, rellena todos los campos del formulario.", Toast.LENGTH_SHORT).show()
                            }

                                val paymentMethod = PaymentMethod(
                                    id = pmInEdit?.id,
                                    cardHolderName = txtCardHolder,
                                    cardNumber = txtCardNumber,
                                    expiryDate = txtExpiration,
                                    cvv = txtCvv,
                                    cardType = txtCardType
                                )

                            if (isEditing){
                                paymentMethodViewModel.updatePaymentMethod(paymentMethod,context)
                            }else{
                                paymentMethodViewModel.insertPaymentMethod(paymentMethod,context)

                            }

                                showDialog = false
                                paymentMethodViewModel.listarPaymentMethods(context)

                        }

                    ) { Text("Guardar")}
                },

                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancelar")
                    }
                },

                text = {
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){

                        OutlinedTextField(
                            value = txtCardHolder,
                            onValueChange = { txtCardHolder = it },
                            label = { Text("Titular") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        )
                        OutlinedTextField(
                            value = txtCardNumber,
                            onValueChange = { txtCardNumber = it },
                            label = { Text("Número de tarjeta") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        )
                        Row(modifier = Modifier.fillMaxWidth()) {
                            OutlinedTextField(
                                value = txtExpiration,
                                onValueChange = { txtExpiration = it },
                                label = { Text("Expira") },
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 4.dp)
                            )
                            OutlinedTextField(
                                value = txtCvv,
                                onValueChange = { txtCvv = it },
                                label = { Text("CVV") },
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 4.dp)
                            )
                        }

                        OutlinedTextField(
                            value = txtCardType,
                            onValueChange = { txtCardType = it },
                            label = { Text("Visa / Matercard") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        )
                    }

                }
            )

        }
        if (showInfoDialog && pmInInfo != null) {
            AlertDialog(
                onDismissRequest = {
                    showInfoDialog = false
                    pmInInfo = null
                },
                title = { Text("Detalles de la Tarjeta") },
                text = {
                    Column {
                        Text("Titular: ${pmInInfo?.cardHolderName ?: "N/A"}")
                        Text("Número: ${pmInInfo?.cardNumber ?: "N/A"}")
                        Text("Expira: ${pmInInfo?.expiryDate ?: "N/A"}")
                        Text("CVV: ${pmInInfo?.cvv ?: "N/A"}")
                        Text("Tipo: ${pmInInfo?.cardType ?: "N/A"}")
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showInfoDialog = false
                            pmInInfo = null
                        }
                    ) {
                        Text("Cerrar")
                    }
                }
            )
        }

    }
}
