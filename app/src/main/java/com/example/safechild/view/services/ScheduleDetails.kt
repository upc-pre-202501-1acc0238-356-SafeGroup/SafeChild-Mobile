import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.safechild.viewmodel.appointments.AppointViewModel
import kotlin.text.get
import kotlin.text.set

@Composable
fun ScheduleDetails(scheduleId: Long?, appointmentViewModel: AppointViewModel) {
    val schedules by appointmentViewModel.schedulesList.collectAsState()
    val schedule = schedules.find { it.id == scheduleId }
    val CustomColor = Color(14, 165, 170, 255)
    val GradientCyan = Color(0, 191, 255, 255)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            if (schedule != null) {
                Text(
                    text = "Detalles del horario",
                    style = MaterialTheme.typography.titleLarge,
                    color = CustomColor,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = CustomColor.copy(alpha = 0.08f),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Fecha: ${schedule.availableDate}",
                                style = MaterialTheme.typography.titleMedium,
                                color = CustomColor
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Turnos para el día: ${schedule.scheduleShifts.size}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
                Text(
                    text = "Turnos:",
                    style = MaterialTheme.typography.titleMedium,
                    color = CustomColor,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                LazyColumn {
                    items(schedule.scheduleShifts.size) { index ->
                        val shift = schedule.scheduleShifts[index]
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                                .clickable(enabled = shift.id != null) {
                                    if (shift.id != null) {
                                        appointmentViewModel.updateShiftAvailability(
                                            shift.id!!,
                                            !shift.available
                                        )
                                    }
                                },
                            shape = RoundedCornerShape(14.dp),
                            elevation = CardDefaults.cardElevation(3.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(
                                        brush = if (shift.available)
                                            Brush.horizontalGradient(
                                                colors = listOf(CustomColor, GradientCyan)
                                            )
                                        else
                                            Brush.horizontalGradient(
                                                colors = listOf(Color.White, Color.White)
                                            ),
                                        shape = RoundedCornerShape(14.dp)
                                    )
                                    .padding(14.dp)
                                    .fillMaxWidth()
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = "Turno: ${shift.shift}",
                                            style = MaterialTheme.typography.bodyLarge,
                                            color = if (shift.available) Color.White else CustomColor
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = if (shift.available) "Disponible" else "No disponible",
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = if (shift.available) Color.White else MaterialTheme.colorScheme.error
                                        )
                                    }
                                    Icon(
                                        imageVector = if (shift.available) Icons.Default.Check else Icons.Default.Close,
                                        contentDescription = null,
                                        tint = if (shift.available) Color.White else MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}