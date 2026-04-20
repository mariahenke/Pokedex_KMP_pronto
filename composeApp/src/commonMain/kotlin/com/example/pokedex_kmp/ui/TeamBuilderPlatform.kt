package com.example.pokedex_kmp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.pokedex_kmp.data.Pokemon

@Composable
expect fun TeamBuilderPlatformContent(
    team: List<Pokemon>,
    onRemovePokemon: (Int) -> Unit,
    backIcon: ImageVector,
    buttonShape: Shape
)
