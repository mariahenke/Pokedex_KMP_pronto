package com.example.pokedex_kmp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.pokedex_kmp.data.Pokemon
import com.example.pokedex_kmp.ui.components.TypeChip

@Composable
actual fun TeamBuilderPlatformContent(
    team: List<Pokemon>,
    onRemovePokemon: (Int) -> Unit,
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(team, key = { it.id }) { pokemon ->
            Card(
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .height(96.dp)
                            .weight(0.35f),
                        contentAlignment = Alignment.Center,
                    ) {
                        Card(
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(96.dp),
                                contentAlignment = Alignment.Center,
                            ) {
                                AsyncImage(model = pokemon.imageUrl, contentDescription = pokemon.name)
                            }
                        }
                    }

                    Column(
                        modifier = Modifier.weight(0.65f),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text(text = pokemon.id.formatPokemonNumber(), style = MaterialTheme.typography.labelMedium)
                        Text(text = pokemon.name, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            pokemon.types.forEach { TypeChip(it) }
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(text = pokemon.ability, style = MaterialTheme.typography.bodyMedium)
                            OutlinedButton(onClick = { onRemovePokemon(pokemon.id) }) { Text("Remover") }
                        }
                    }
                }
            }
        }
    }
}
