package com.example.pokedex_kmp.ui

import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(22.dp))
                    .background(Color.White)
                    .padding(14.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .weight(0.32f)
                        .clip(RoundedCornerShape(18.dp))
                        .background(Color(0xFFF4F6FA))
                        .height(90.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    AsyncImage(model = pokemon.imageUrl, contentDescription = pokemon.name)
                }
                Column(
                    modifier = Modifier.weight(0.68f),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(text = pokemon.name, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Medium)
                    Text(text = pokemon.id.formatPokemonNumber(), style = MaterialTheme.typography.labelMedium, color = Color.Gray)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        pokemon.types.forEach { TypeChip(it) }
                    }
                    Text(text = pokemon.category, style = MaterialTheme.typography.bodyMedium, color = Color(0xFF5B6470))
                    OutlinedButton(onClick = { onRemovePokemon(pokemon.id) }) {
                        Text("Remover")
                    }
                }
            }
        }
    }
}
