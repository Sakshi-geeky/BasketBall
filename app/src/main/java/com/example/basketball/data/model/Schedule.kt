package com.example.basketball.data.model



data class Schedule(
    val uid: String,
    val gametime: String,
    val arena_name: String,
    val arena_city: String,
    val arena_state: String,
    val st: Int, // 1: Future, 2: Live, 3: Past
    val h: Team,
    val v: Team
)