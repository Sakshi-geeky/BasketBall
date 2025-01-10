package com.example.basketball.ui.schedule.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.basketball.data.model.TeamInfo
import androidx.compose.ui.Modifier

@Composable
fun TeamDisplay(
    team: TeamInfo?,
    score: String,
    isHome: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.width(80.dp)
    ) {

        AsyncImage(
            model = team?.logo,
            contentDescription = "${team?.tn} logo",
modifier = Modifier
.size(40.dp)
.padding(bottom = 4.dp),
contentScale = ContentScale.Fit
)


Text(
text = team?.tn ?: "TBD",
style = MaterialTheme.typography.titleMedium,
textAlign = TextAlign.Center,
color = Color.White,
fontWeight = FontWeight.Bold,
modifier = Modifier.padding(vertical = 2.dp)
)


if (score.isNotEmpty()) {
    Text(
        text = score,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Center,
        color = Color.White,
        fontWeight = FontWeight.Bold
    )
}
}
}