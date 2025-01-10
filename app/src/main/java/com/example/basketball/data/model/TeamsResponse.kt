package com.example.basketball.data.model

data class TeamsResponse(
    val data: TeamsData
)

data class TeamsData(
    val teams: List<TeamInfo>
)
