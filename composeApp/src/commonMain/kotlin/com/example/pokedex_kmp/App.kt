package com.example.pokedex_kmp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.pokedex_kmp.data.PokemonRepository
import com.example.pokedex_kmp.navigation.HomeRoute
import com.example.pokedex_kmp.navigation.PokedexRoute
import com.example.pokedex_kmp.navigation.PokemonDetailRoute
import com.example.pokedex_kmp.navigation.TeamRoute
import com.example.pokedex_kmp.ui.HomeScreen
import com.example.pokedex_kmp.ui.PokedexGridScreen
import com.example.pokedex_kmp.ui.PokemonDetailScreen
import com.example.pokedex_kmp.ui.TeamBuilderScreen

private data class BottomDestination(
    val label: String,
    val route: Any,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        val pokemons = remember { PokemonRepository.getPokemonList() }
        val team = remember { mutableStateListOf<Int>() }
        val backStackEntry = navController.currentBackStackEntryAsState().value

        val bottomDestinations = listOf(
            BottomDestination(label = "Pokédex", route = PokedexRoute),
            BottomDestination(label = "Meu time", route = TeamRoute),
        )

        val title = when {
            backStackEntry?.destination?.hasRoute<HomeRoute>() == true -> "Pokédex Multiplatform"
            backStackEntry?.destination?.hasRoute<PokedexRoute>() == true -> "Pokédex"
            backStackEntry?.destination?.hasRoute<TeamRoute>() == true -> "Team Builder"
            backStackEntry?.destination?.hasRoute<PokemonDetailRoute>() == true -> "Detalhes"
            else -> "Pokédex Multiplatform"
        }

        val canGoBack = navController.previousBackStackEntry != null

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(title) },
                    navigationIcon = {
                        if (canGoBack) {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Voltar"
                                )
                            }
                        }
                    }
                )
            },
            bottomBar = {
                NavigationBar {
                    bottomDestinations.forEach { destination ->
                        val selected = when (destination.route) {
                            PokedexRoute -> backStackEntry?.destination?.hasRoute<PokedexRoute>() == true
                            TeamRoute -> backStackEntry?.destination?.hasRoute<TeamRoute>() == true
                            else -> false
                        }
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                navController.navigate(destination.route) {
                                    popUpTo(HomeRoute) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Text(if (destination.label == "Pokédex") "◉" else "★") },
                            label = { Text(destination.label) },
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
                        teamIds = team,
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
                        team = team.mapNotNull(PokemonRepository::getPokemonById),
                        onRemovePokemon = { team.remove(it) },
                        onExplorePokedex = { navController.navigate(PokedexRoute) },
                    )
                }
            }
        }
    }
}
