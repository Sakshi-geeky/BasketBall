package com.example.basketball.ui.schedule

import android.util.Log

import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope

import com.example.basketball.data.model.Schedule

import com.example.basketball.data.model.TeamInfo

import com.example.basketball.repository.ScheduleRepository

import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.flow.asStateFlow

import kotlinx.coroutines.launch

import java.text.SimpleDateFormat

import java.time.LocalDateTime

import java.time.format.DateTimeFormatter

import java.util.Date

import java.util.Locale

import javax.inject.Inject



@HiltViewModel

class ScheduleViewModel @Inject constructor(

    private val repository: ScheduleRepository

) : ViewModel() {

    private var availableMonths = listOf<String>()

    private var currentMonthIndex = 0

    private val _currentMonth = MutableStateFlow<String>("")

    val currentMonth = _currentMonth.asStateFlow()





    fun updateCurrentMonth(month: String) {

        _currentMonth.value = month

    }

    fun navigateToNextMonth() {

        if (currentMonthIndex < availableMonths.size - 1) {

            currentMonthIndex++

            _currentMonth.value = availableMonths[currentMonthIndex]

        }

    }



    fun navigateToPreviousMonth() {

        if (currentMonthIndex > 0) {

            currentMonthIndex--

            _currentMonth.value = availableMonths[currentMonthIndex]

        }

    }





    private val _scheduleState = MutableStateFlow<ScheduleUiState>(ScheduleUiState.Loading)

    val scheduleState = _scheduleState.asStateFlow()



    private val _searchQuery = MutableStateFlow("")

    val searchQuery = _searchQuery.asStateFlow()



    private val APP_TEAM_ID = "1610612748" // Miami Heat



    init {

        loadScheduleData()

    }



    fun updateSearchQuery(query: String) {

        _searchQuery.value = query

        filterSchedule()

    }







    private fun loadScheduleData() {

        viewModelScope.launch {

            try {

                _scheduleState.value = ScheduleUiState.Loading





                val schedules = repository.getScheduleData()

                Log.d("ScheduleViewModel", "Loaded ${schedules.size} schedules")

                val teams = repository.getTeamsData()





                val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)

                val monthYearFormat = SimpleDateFormat("MMMM yyyy", Locale.US)



                val groupedSchedules = schedules

                    .sortedBy { dateFormat.parse(it.gametime)?.time ?: 0L }

                    .groupBy { schedule ->

                        val date = dateFormat.parse(schedule.gametime)

                        monthYearFormat.format(date ?: Date())

                    }



                Log.d("groupedSchedules", "${groupedSchedules.toString()}")

                availableMonths = groupedSchedules.keys.toList()

                currentMonthIndex = 0

                _currentMonth.value = availableMonths.firstOrNull() ?: ""



                _scheduleState.value = ScheduleUiState.Success(

                    schedules = groupedSchedules,

                    teams = teams

                )

            } catch (e: Exception) {

                Log.e("viewmodel exception", "Error: ${e.message}", e)

                _scheduleState.value = ScheduleUiState.Error(e.message ?: "Unknown error")

            }

        }

    }





    private fun filterSchedule() {

        val currentState = _scheduleState.value as? ScheduleUiState.Success ?: return

        val query = _searchQuery.value.lowercase()



        if (query.isEmpty()) {

            loadScheduleData()

            return

        }



        viewModelScope.launch {

            val filteredSchedules = currentState.schedules.mapValues { (_, games) ->

                games.filter { schedule ->

                    schedule.arena_name.lowercase().contains(query) ||

                            schedule.arena_city.lowercase().contains(query) ||

                            currentState.teams[schedule.h.tid]?.tn?.lowercase()?.contains(query) == true ||

                            currentState.teams[schedule.v.tid]?.tn?.lowercase()?.contains(query) == true

                }

            }.filterValues { it.isNotEmpty() }



            _scheduleState.value = currentState.copy(schedules = filteredSchedules)

        }

    }

}





sealed class ScheduleUiState {

    object Loading : ScheduleUiState()

    data class Success(

        val schedules: Map<String, List<Schedule>>,

        val teams: Map<String, TeamInfo>

    ) : ScheduleUiState()

    data class Error(val message: String) : ScheduleUiState()

}