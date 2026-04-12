package com.example.pokedex_kmp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.pokedex_kmp.data.Pokemon
import com.example.pokedex_kmp.data.PokemonMock
import com.example.pokedex_kmp.ui.components.StatIndicator
import com.example.pokedex_kmp.ui.components.TypeChip

@Composable
fun PokemonDetailScreen(
    pokemon: Pokemon?,
    isInTeam: Boolean,
    onBackClick: () -> Unit,
    onAddToTeam: (Int) -> Unit,
) {
    if (pokemon == null) {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            OutlinedButton(onClick = onBackClick) { Text("Voltar") }
            Text("Pokémon não encontrado.")
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
       // OutlinedButton(onClick = onBackClick) { Text("Voltar") }

        Card(
            shape = RoundedCornerShape(28.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
                        .background(pokemonTypeGradient(pokemon.types))
                        .padding(24.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    AsyncImage(
                        model = pokemon.imageUrl,
                        contentDescription = pokemon.name)
                }

                Column(
                    modifier = Modifier.padding(horizontal = 18.dp, vertical = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                ) {
                    Text(text = pokemon.id.formatPokemonNumber(), style = MaterialTheme.typography.labelLarge)
                    Text(text = pokemon.name, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)

                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        pokemon.types.forEach { type -> TypeChip(type = type) }
                    }

                    Text(text = pokemon.description, style = MaterialTheme.typography.bodyLarge)

                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        DetailMiniCard(title = "Peso", value = "${pokemon.weightKg} kg", modifier = Modifier.weight(1f))
                        DetailMiniCard(title = "Altura", value = "${pokemon.heightMeters} m", modifier = Modifier.weight(1f))
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        DetailMiniCard(title = "Categoria", value = pokemon.category, modifier = Modifier.weight(1f))
                        DetailMiniCard(title = "Habilidade", value = pokemon.ability, modifier = Modifier.weight(1f))
                    }

                    Text(text = "Stats", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
                    pokemon.stats.forEach { stat ->
                        StatIndicator(stat)
                    }

                    Button(
                        onClick = { onAddToTeam(pokemon.id) },
                        enabled = !isInTeam,
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                    ) {
                        Text(if (isInTeam) "Pokémon já está no time" else "Adicionar ao time")
                    }
                }
            }
        }
    }
}

@Composable
private fun DetailMiniCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(text = title, style = MaterialTheme.typography.labelMedium)
            Text(text = value, style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Preview
@Composable
fun PokemonDetailScreenPreview() {
    MaterialTheme {
        PokemonDetailScreen(
            pokemon = PokemonMock.pokedex.first(),
            isInTeam = false,
            onBackClick = {},
            onAddToTeam = {}
        )
    }
}
