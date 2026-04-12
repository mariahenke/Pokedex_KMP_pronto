package com.example.pokedex_kmp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    teamSize: Int,
    onGoToPokedex: () -> Unit,
    onGoToTeam: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        Card(
            shape = RoundedCornerShape(28.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Brush.verticalGradient(listOf(Color(0xFF2E6CE6), Color(0xFF6ED3FF))))
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            )

            {
                Text("Sua aventura começa aqui", style = MaterialTheme.typography.headlineMedium, color = Color.White, fontWeight = FontWeight.Bold)
                Text(
                    "Explore a Pokédex, visualize detalhes e monte um time com até 6 Pokémons.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White.copy(alpha = 0.92f),
                )
                Button(onClick = onGoToPokedex) { Text("Explorar Pokédex") }
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            DashboardInfoCard(modifier = Modifier.weight(1f), title = "Pokémons", value = "12", symbol = "◉")
            DashboardInfoCard(modifier = Modifier.weight(1f), title = "No time", value = "$teamSize/6", symbol = "★")
        }

    }
}

@Composable
private fun DashboardInfoCard(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    symbol: String,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(symbol, style = MaterialTheme.typography.headlineSmall)
            Text(text = title, style = MaterialTheme.typography.labelLarge)
            Text(text = value, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        }
    }
}
