package com.borjadelgadodev.freetogame.ui.screens.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.lifecycle.viewmodel.compose.viewModel
import com.borjadelgadodev.freetogame.ui.screens.detail.DetailScreen
import com.borjadelgadodev.freetogame.ui.screens.detail.DetailViewModel
import com.borjadelgadodev.freetogame.ui.screens.home.HomeScreen
import com.borjadelgadodev.freetogame.ui.screens.home.HomeViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val homeViewModel: HomeViewModel = viewModel()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        NavHost(navController = navController, startDestination = "home", modifier = Modifier.padding(paddingValues)
        ) {
            composable("home") {
                homeViewModel.onUiReady()
                HomeScreen(onClick = { game -> navController.navigate("detail/${game.id}")
                })
            }
            composable("games") {
                //GamesScreen() // La pantalla "Juegos" que definimos
            }
            composable("my_games") {
                //MyGamesScreen() // La pantalla "Mis Juegos" que definimos
            }
            composable("my_profile") {
                //MyProfileScreen() // La pantalla "Perfil" que definimos
            }
            composable("detail/{gameId}",
                arguments = listOf(navArgument("gameId") { type = NavType.IntType })
            ) { backStackEntry ->
                val gameId = requireNotNull(backStackEntry.arguments?.getInt("gameId"))
                DetailScreen(
                    DetailViewModel(gameId),
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}