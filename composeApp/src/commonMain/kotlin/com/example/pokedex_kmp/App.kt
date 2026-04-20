package com.example.pokedex_kmp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.CatchingPokemon
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.*
import androidx.navigation.toRoute
import com.example.pokedex_kmp.data.PokemonRepository
import com.example.pokedex_kmp.navigation.*
import com.example.pokedex_kmp.ui.*

private data class BottomDestination(
    val label: String,
    val route: Any,
    val icon: ImageVector
)

@Composable
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        val pokemons = remember { PokemonRepository.getPokemonList() }
        val team = remember { mutableStateListOf<Int>() }

        val backStackEntry by navController.currentBackStackEntryAsState()

        val bottomDestinations = listOf(
            BottomDestination(label = "Início", route = HomeRoute, icon = Icons.Default.Home),
            BottomDestination(label = "Pokédex", route = PokedexRoute, icon = Icons.Default.CatchingPokemon),
            BottomDestination(label = "Meu time", route = TeamRoute, icon = Icons.Default.Groups),
        )

        Scaffold(
            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 8.dp
                ) {
                    bottomDestinations.forEach { destination ->
                        val selected = backStackEntry?.destination?.hasRoute(destination.route::class) == true

                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                navController.navigate(destination.route) {
                                    popUpTo(HomeRoute) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = destination.icon,
                                    contentDescription = destination.label
                                )
                            },
                            label = { Text(destination.label) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.White,
                                indicatorColor = Color(0xFF6A1B9A), // O círculo de seleção roxo
                                selectedTextColor = Color(0xFF6A1B9A),
                                unselectedIconColor = Color.Gray,
                                unselectedTextColor = Color.Gray
                            )
                        )
                    }
                }
            },
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = HomeRoute,
                modifier = Modifier.padding(innerPadding),
            ) {
                composable<HomeRoute> {
                    HomeScreen(
                        teamSize = team.size,
                        onGoToPokedex = { navController.navigate(PokedexRoute) },
                        onGoToTeam = { navController.navigate(TeamRoute) },
                    )
                }

                composable<PokedexRoute> {
                    PokedexGridScreen(
                        pokemons = pokemons,
                        team = team,
                        onPokemonClick = { navController.navigate(PokemonDetailRoute(it)) },
                    )
                }

                composable<PokemonDetailRoute> { entry ->
                    val route = entry.toRoute<PokemonDetailRoute>()
                    PokemonDetailScreen(
                        pokemon = PokemonRepository.getPokemonById(route.pokemonId),
                        isInTeam = team.contains(route.pokemonId),
                        onBackClick = { navController.popBackStack() },
                        onAddToTeam = { pokemonId ->
                            if (!team.contains(pokemonId) && team.size < 6) team.add(pokemonId)
                        },
                    )
                }

                composable<TeamRoute> {
                    TeamBuilderScreen(
                        team = team.mapNotNull { id -> PokemonRepository.getPokemonById(id) },
                        onRemovePokemon = { id -> team.remove(id) },
                        onPokemonClick = { id ->
                            navController.navigate(PokemonDetailRoute(id))
                        },
                        onExplorePokedex = {
                            navController.navigate(PokedexRoute)
                        }
                    )
                }
            }
        }
    }
}
