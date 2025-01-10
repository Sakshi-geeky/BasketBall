package com.example.basketball.repository

import android.content.Context
import android.util.Log
import com.example.basketball.data.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import java.lang.reflect.Type
class ScheduleRepository @Inject constructor(
    private val context: Context
) {
    suspend fun getScheduleData(): List<Schedule> {
        return withContext(Dispatchers.IO) {
            try {
                val jsonString = context.assets.open("schedule.json").bufferedReader().use { it.readText() }
                val scheduleListType: Type = object : TypeToken<ScheduleResponse>() {}.type
                val response: ScheduleResponse = Gson().fromJson(jsonString, scheduleListType)
                Log.d("json response", "${response.data.schedules}")
                response.data.schedules
            } catch (e: Exception) {
                Log.d("json response EXCEPTION", "${e.toString()}")
                e.printStackTrace()
                emptyList()
            }
        }
    }

    suspend fun getTeamsData(): Map<String, TeamInfo> {
        return withContext(Dispatchers.IO) {
            try {
                val jsonString = context.assets.open("teams.json").bufferedReader().use { it.readText() }
                val teamsListType: Type = object : TypeToken<TeamsResponse>() {}.type
                val response: TeamsResponse = Gson().fromJson(jsonString, teamsListType)
                response.data.teams.associateBy { it.tid }
            } catch (e: Exception) {
                e.printStackTrace()
                emptyMap()
            }
        }
    }
}