package com.example.pokedex_kmp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.pokedex_kmp.data.Pokemon

@Composable
fun TeamBuilderScreen(
    team: List<Pokemon>,
    onRemovePokemon: (Int) -> Unit,
    onExplorePokedex: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Text(
            text = "Monte seu time",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "Tela implementada com expect/actual para diferenciar a estética entre Android e iOS.",
            style = MaterialTheme.typography.bodyMedium,
        )

        if (team.isEmpty()) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "Seu time ainda está vazio. Vá para a Pokédex e adicione até 6 Pokémons.",
                    style = MaterialTheme.typography.bodyLarge,
                )
                Button(onClick = onExplorePokedex, modifier = Modifier.fillMaxWidth()) {
                    Text("Explorar Pokédex")
                }
            }
        } else {
            TeamBuilderPlatformContent(
                team = team,
                onRemovePokemon = onRemovePokemon,
            )
        }
    }
}
