package com.example.pokedex_kmp.data

data class PokemonStat(
    val name: String,
    val value: Int,
)

data class Pokemon(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val types: List<String>,
    val heightMeters: Double,
    val weightKg: Double,
    val description: String,
    val ability: String,
    val category: String,
    val stats: List<PokemonStat>,
)
