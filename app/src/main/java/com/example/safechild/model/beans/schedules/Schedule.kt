package com.example.safechild.model.beans.schedules

data class Schedule(
    val id: Long,
    val caregiverId: Long,
    val availableDate: String,
    val scheduleShifts: List<ScheduleShift>
)