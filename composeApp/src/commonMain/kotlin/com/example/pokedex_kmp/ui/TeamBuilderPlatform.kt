package com.example.pokedex_kmp.ui

import androidx.compose.runtime.Composable
import com.example.pokedex_kmp.data.Pokemon

@Composable
expect fun TeamBuilderPlatformContent(
    team: List<Pokemon>,
    onRemovePokemon: (Int) -> Unit,
)
