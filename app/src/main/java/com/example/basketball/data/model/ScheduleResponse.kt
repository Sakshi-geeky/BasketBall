package com.example.basketball.data.model


data class ScheduleResponse(
    val data: ScheduleData
)

data class ScheduleData(
    val schedules: List<Schedule>
)