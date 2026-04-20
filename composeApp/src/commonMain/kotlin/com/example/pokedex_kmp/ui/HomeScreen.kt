package com.example.pokedex_kmp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CatchingPokemon
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    teamSize: Int,
    onGoToPokedex: () -> Unit,
    onGoToTeam: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Banner Principal (Hero Section)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF6A1B9A), Color(0xFFAB47BC))
                    ),
                    shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
                )
                .padding(top = 60.dp, bottom = 40.dp, start = 24.dp, end = 24.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Badge(
                    containerColor = Color.White.copy(alpha = 0.2f),
                    contentColor = Color.White
                ) {
                    Text(
                        "Treinador Nível 1",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                Text(
                    text = "Sua jornada\nPokémon começa!",
                    style = MaterialTheme.typography.displaySmall,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    lineHeight = 40.sp
                )

                Text(
                    text = "Capture, visualize detalhes e monte o time dos seus sonhos.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White.copy(alpha = 0.8f)
                )

                Button(
                    onClick = onGoToPokedex,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
                ) {
                    Icon(Icons.Default.Explore, contentDescription = null, tint = Color(0xFF6A1B9A))
                    Spacer(Modifier.width(8.dp))
                    Text("Explorar Pokédex", color = Color(0xFF6A1B9A), fontWeight = FontWeight.Bold)
                }
            }
        }

        // Dashboard de Status
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "Resumo da Aventura",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                StatusCard(
                    modifier = Modifier.weight(1f),
                    title = "No Time",
                    value = "$teamSize/6",
                    icon = Icons.Default.Groups,
                    color = Color(0xFF6A1B9A),
                    onClick = onGoToTeam
                )
                StatusCard(
                    modifier = Modifier.weight(1f),
                    title = "Vistos",
                    value = "151",
                    icon = Icons.Default.CatchingPokemon,
                    color = Color(0xFFAB47BC),
                    onClick = onGoToPokedex
                )
            }

            // Card de Atalho para o Time
            Card(
                onClick = onGoToTeam,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF6A1B9A).copy(alpha = 0.05f)
                )
            ) {
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "⚡",
                        fontSize = 32.sp
                    )
                    Spacer(Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Gerenciar minha equipe",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Toque para ver seus pokémons selecionados",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StatusCard(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    icon: ImageVector,
    color: Color,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier.height(140.dp),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier
                    .size(32.dp)
                    .background(color.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                    .padding(6.dp)
            )
            Column {
                Text(
                    text = value,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = color
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}