package com.example.basketball.ui.schedule

import androidx.compose.foundation.ExperimentalFoundationApi

import androidx.compose.foundation.background

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.KeyboardArrowDown

import androidx.compose.material.icons.filled.KeyboardArrowUp

import androidx.compose.material.icons.filled.Search

import androidx.compose.material3.*

import androidx.compose.runtime.Composable

import androidx.compose.runtime.LaunchedEffect

import androidx.compose.runtime.collectAsState

import androidx.compose.runtime.getValue

import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp

import androidx.hilt.navigation.compose.hiltViewModel

import com.example.basketball.data.model.Schedule

import com.example.basketball.data.model.TeamInfo

import com.example.basketball.ui.schedule.components.TeamDisplay

import java.text.SimpleDateFormat

import java.util.*






@Composable

fun ScheduleScreen(

    viewModel: ScheduleViewModel = hiltViewModel()

) {

    val scheduleState by viewModel.scheduleState.collectAsState()

    val currentMonth by viewModel.currentMonth.collectAsState()

    val searchQuery by viewModel.searchQuery.collectAsState()



    Column(

        modifier = Modifier

            .fillMaxSize()

            .background(Color.Black)

    ) {



        Header(

            currentMonth = currentMonth,

            onMonthChange = viewModel::updateCurrentMonth,

            searchQuery = searchQuery,

            onSearchQueryChange = viewModel::updateSearchQuery

        )





        when (val state = scheduleState) {

            is ScheduleUiState.Loading -> LoadingIndicator()

            is ScheduleUiState.Error -> ErrorMessage(state.message)

            is ScheduleUiState.Success -> GamesList(

                schedules = state.schedules,

                teams = state.teams,

                onMonthChange = viewModel::updateCurrentMonth,

                viewModel = viewModel

            )

        }

    }

}



@OptIn(ExperimentalMaterial3Api::class)

@Composable

fun Header(

    currentMonth: String,

    onMonthChange: (String) -> Unit,

    searchQuery: String,

    onSearchQueryChange: (String) -> Unit

) {

    Column(

        modifier = Modifier

            .fillMaxWidth()

            .padding(16.dp)

    ) {



//        Row(

//            modifier = Modifier.fillMaxWidth(),

//            horizontalArrangement = Arrangement.SpaceBetween

//        ) {

        Text(

            text = "TEAM",

            style = MaterialTheme.typography.titleLarge,

            color = Color.White,

            fontWeight = FontWeight.Bold,

            modifier = Modifier.fillMaxWidth(),

            textAlign = TextAlign.Center

        )



        Spacer(modifier = Modifier.height(16.dp))



        // Schedule and Games with underline

        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceEvenly

        ) {

            Column(

                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                Text(

                    text = "Schedule",

                    style = MaterialTheme.typography.titleMedium,

                    color = Color(0xFFFFD700), // Gold color for active tab

                )

                // Gold underline for active tab

                Box(

                    modifier = Modifier

                        .width(80.dp)

                        .height(2.dp)

                        .background(Color(0xFFFFD700))

                )

            }



            Column(

                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                Text(

                    text = "Games",

                    style = MaterialTheme.typography.titleMedium,

                    color = Color.Gray,

                    )

                // Transparent box to maintain layout

                Box(

                    modifier = Modifier

                        .width(80.dp)

                        .height(2.dp)

                )

            }

        }



        Spacer(modifier = Modifier.height(16.dp))



        OutlinedTextField(

            value = searchQuery,

            onValueChange = onSearchQueryChange,

            modifier = Modifier

                .fillMaxWidth()

                .padding(vertical = 8.dp),

            placeholder = {

                Text(

                    text = "Search by team, arena, or city...",

                    color = Color.Gray

                )

            },

            leadingIcon = {

                Icon(

                    imageVector = Icons.Default.Search,

                    contentDescription = "Search",

                    tint = Color.Gray

                )

            },

            colors = TextFieldDefaults.outlinedTextFieldColors(

                focusedTextColor = Color.White,

                cursorColor = Color(0xFFFFD700),

                focusedBorderColor = Color(0xFFFFD700),

                unfocusedBorderColor = Color.DarkGray,

                containerColor = Color(0xFF1C1C1C)

            ),

            shape = RoundedCornerShape(8.dp),

            singleLine = true

        )



        Spacer(modifier = Modifier.height(16.dp))





        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween,

            verticalAlignment = Alignment.CenterVertically

        ) {

//            Text(

//                text = currentMonth,

//                color = Color(0xFFFFD700), // Gold color

//                fontWeight = FontWeight.Bold

//            )

            Row {

//                Icon(

//                    imageVector = Icons.Default.KeyboardArrowUp,

//                    contentDescription = "Previous month",

//                    tint = Color(0xFFFFD700)

//                )

//                Icon(

//                    imageVector = Icons.Default.KeyboardArrowDown,

//                    contentDescription = "Next month",

//                    tint = Color(0xFFFFD700)

//                )

            }

        }

    }

}



@OptIn(ExperimentalFoundationApi::class)

@Composable

fun GamesList(

    schedules: Map<String, List<Schedule>>,

    teams: Map<String, TeamInfo>,

    onMonthChange: (String) -> Unit,

    viewModel: ScheduleViewModel = hiltViewModel()

) {

    LazyColumn(

        modifier = Modifier

            .fillMaxSize()

            .padding(horizontal = 16.dp)

    ) {

        schedules.forEach { (month, games) ->

            stickyHeader {

                Box(

                    modifier = Modifier

                        .fillMaxWidth()

                        .background(Color(0xFF1C1C1C))

                        .padding(vertical = 8.dp)

                ) {

                    Row(

                        modifier = Modifier

                            .fillMaxWidth()

                            .padding(horizontal = 16.dp),

                        horizontalArrangement = Arrangement.SpaceBetween,

                        verticalAlignment = Alignment.CenterVertically

                    ) {

                        IconButton(

                            onClick = { viewModel.navigateToPreviousMonth() }

                        ) {

                            Icon(

                                imageVector = Icons.Default.KeyboardArrowUp,

                                contentDescription = "Previous month",

                                tint = Color(0xFFFFD700)

                            )

                        }



                        Text(

                            text = month,

                            color = Color(0xFFFFD700),

                            fontWeight = FontWeight.Bold

                        )

                        IconButton(

                            onClick = { viewModel.navigateToNextMonth() }

                        ) {



                            Icon(

                                imageVector = Icons.Default.KeyboardArrowDown,

                                contentDescription = "Next month",

                                tint = Color(0xFFFFD700)

                            )

                        }

                    }

                }

            }



            items(games) { game ->

                LaunchedEffect(month) {

                    onMonthChange(month)

                }

                GameCard(game = game, teams = teams)

                Spacer(modifier = Modifier.height(8.dp))

            }

        }

    }

}

@Composable

fun GameCard(

    game: Schedule,

    teams: Map<String, TeamInfo>

) {

    Card(

        modifier = Modifier

            .fillMaxWidth(),

        shape = RoundedCornerShape(16.dp),

        colors = CardDefaults.cardColors(

            containerColor = Color(0xFF1C1C1C)

        )

    ) {

        Column(

            modifier = Modifier.padding(16.dp)

        ) {



            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceBetween,

                verticalAlignment = Alignment.CenterVertically

            ) {



                Row(

                    verticalAlignment = Alignment.CenterVertically,

                    horizontalArrangement = Arrangement.spacedBy(4.dp)

                ) {

                    Text(

                        text = if (game.h.tid == "1610612748") "HOME" else "AWAY",

                        color = Color.Gray,

                        style = MaterialTheme.typography.bodyMedium

                    )

                    Text(

                        text = "|",

                        color = Color.Gray,

                        style = MaterialTheme.typography.bodyMedium

                    )

                    Text(

                        text = formatGameTime(game.gametime),

                        color = Color.Gray,

                        style = MaterialTheme.typography.bodyMedium

                    )

                }





                when (game.st) {

                    2 -> Box(

                        modifier = Modifier

                            .background(

                                color = Color.Black,

                                shape = RoundedCornerShape(4.dp)

                            )

                            .padding(horizontal = 8.dp, vertical = 4.dp)

                    ) {

                        Text(

                            text = "LIVE",

                            color = Color.Red,

                            fontWeight = FontWeight.Bold,

                            style = MaterialTheme.typography.labelMedium

                        )

                    }

                    3 -> Text(

                        text = "FINAL",

                        color = Color.Gray,

                        style = MaterialTheme.typography.bodyMedium

                    )

                }

            }



            if (game.st == 2) {

                Spacer(modifier = Modifier.height(4.dp))

                Text(

                    text = "3RD QTR | 00:16.3",  // This should come from game data

                    color = Color.Gray,

                    style = MaterialTheme.typography.bodyMedium,

                    modifier = Modifier.align(Alignment.CenterHorizontally)

                )

            }



            Spacer(modifier = Modifier.height(8.dp))





            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceEvenly,

                verticalAlignment = Alignment.CenterVertically

            ) {

                TeamDisplay(

                    team = teams[game.v.tid],

                    score = game.v.s,

                    isHome = false

                )



                Text(

                    text = when(game.st) {

                        1 -> "vs"

                        2 -> "vs"

                        else -> "@"

                    },

                    color = Color.White,

                    style = MaterialTheme.typography.titleLarge,

                    fontWeight = FontWeight.Bold

                )



                TeamDisplay(

                    team = teams[game.h.tid],

                    score = game.h.s,

                    isHome = true

                )

            }





            if (game.st == 1) {

                Spacer(modifier = Modifier.height(8.dp))

                Text(

                    text = "${game.arena_name}, ${game.arena_city}",

                    color = Color.Gray,

                    style = MaterialTheme.typography.bodyMedium,

                    modifier = Modifier.align(Alignment.CenterHorizontally)

                )



                Spacer(modifier = Modifier.height(8.dp))

                Button(

                    onClick = { },

                    colors = ButtonDefaults.buttonColors(

                        containerColor = Color(0xFFFFD700)

                    ),

                    shape = RoundedCornerShape(8.dp),

                    modifier = Modifier.fillMaxWidth()

                ) {

                    Text(

                        text = "BUY TICKETS ON ticketmaster",

                        color = Color.Black,

                        style = MaterialTheme.typography.labelLarge,

                        fontWeight = FontWeight.Bold

                    )

                }

            }

        }

    }

}





@Composable

fun TeamScore(

    team: com.example.basketball.data.model.Team,

    teamInfo: TeamInfo?,

    isHome: Boolean

) {

    Row(

        verticalAlignment = Alignment.CenterVertically,

        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ) {



        Text(

            text = team.tn,

            color = Color.White,

            fontWeight = FontWeight.Bold

        )

        if (team.s.isNotEmpty()) {

            Text(

                text = team.s,

                color = Color.White

            )

        }

    }

}



private fun formatGameTime(dateTimeStr: String): String {

    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)

    val outputFormat = SimpleDateFormat("EEE MMM dd | h:mm a", Locale.US)

    return try {

        val date = inputFormat.parse(dateTimeStr)

        outputFormat.format(date)

    } catch (e: Exception) {

        dateTimeStr

    }

}





@Composable

fun LoadingIndicator() {

    Box(

        modifier = Modifier

            .fillMaxSize()

            .background(Color.Black),

        contentAlignment = Alignment.Center

    ) {

//        CircularProgressIndicator(

//            color = Color(0xFFFFD700) // Gold color to match theme

//        )

    }

}



@Composable

fun ErrorMessage(message: String) {

    Box(

        modifier = Modifier

            .fillMaxSize()

            .background(Color.Black),

        contentAlignment = Alignment.Center

    ) {

        Text(

            text = message,

            color = Color.White

        )

    }

}
