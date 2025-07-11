package com.example.safechild.model.beans.schedules

data class ScheduleShift(
    val id: Long,
    val schedule: ScheduleReference,
    val shift: String,
    val available: Boolean
)

data class ScheduleReference(
    val id: Long,
    val createdAt: String,
    val updatedAt: String,
    val caregiverId: Long,
    val availableDate: String,
    val scheduleShifts: List<String>
)