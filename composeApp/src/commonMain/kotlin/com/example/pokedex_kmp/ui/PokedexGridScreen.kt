package com.example.pokedex_kmp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.pokedex_kmp.data.Pokemon
import com.example.pokedex_kmp.ui.components.TypeChip

@Composable
fun PokedexGridScreen(
    pokemons: List<Pokemon>,
    teamIds: List<Int>,
    onPokemonClick: (Int) -> Unit,
) {
    var search by remember { mutableStateOf("") }

    val filteredList = remember(search, pokemons) {
        pokemons.filter {
            it.name.contains(search, ignoreCase = true) ||
                it.types.any { type -> type.contains(search, ignoreCase = true) }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = "Encontre seu próximo parceiro",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 12.dp),
        )
        Text(
            text = "Grid com LazyVerticalGrid, nome, número e tipos, conforme o enunciado.",
            style = MaterialTheme.typography.bodyMedium,
        )

        OutlinedTextField(
            value = search,
            onValueChange = { search = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            leadingIcon = { Text("⌕") },
            placeholder = { Text("Buscar por nome ou tipo") },
            shape = RoundedCornerShape(18.dp),
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(filteredList, key = { it.id }) { pokemon ->
                PokemonGridItem(
                    pokemon = pokemon,
                    isInTeam = teamIds.contains(pokemon.id),
                    onClick = { onPokemonClick(pokemon.id) },
                )
            }
        }
    }
}

@Composable
private fun PokemonGridItem(
    pokemon: Pokemon,
    isInTeam: Boolean,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(pokemonTypeGradient(pokemon.types)),
            ) {
                AsyncImage(
                    model = pokemon.imageUrl,
                    contentDescription = pokemon.name,
                    modifier = Modifier.align(Alignment.Center),
                )
                if (isInTeam) {
                    Text(
                        text = "♥",
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(10.dp),
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }

            Text(text = pokemon.id.formatPokemonNumber(), style = MaterialTheme.typography.labelMedium)
            Text(text = pokemon.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                pokemon.types.forEach { type ->
                    TypeChip(type = type)
                }
            }
        }
    }
}
