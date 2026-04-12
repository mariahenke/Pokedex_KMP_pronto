package com.example.pokedex_kmp.ui

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun Int.formatPokemonNumber(): String = "N°${toString().padStart(3, '0')}"

fun pokemonTypeColor(type: String): Color = when (type.lowercase()) {
    "grama" -> Color(0xFF63BC5A)
    "venenoso" -> Color(0xFFB567CE)
    "fogo" -> Color(0xFFF5A156)
    "água" -> Color(0xFF539DDF)
    "eletrico", "elétrico" -> Color(0xFFF2D94E)
    "normal" -> Color(0xFF9FA19F)
    "fada" -> Color(0xFFEC8FE6)
    "lutador" -> Color(0xFFCE416B)
    "metal" -> Color(0xFF5A8EA1)
    "fantasma" -> Color(0xFF5269AD)
    else -> Color(0xFF7A7A7A)
}

fun pokemonTypeGradient(types: List<String>): Brush {
    val colors = types.take(2).map(::pokemonTypeColor)
    return Brush.linearGradient(
        colors = if (colors.size == 1) listOf(colors.first(), colors.first().copy(alpha = 0.75f)) else colors,
    )
}
