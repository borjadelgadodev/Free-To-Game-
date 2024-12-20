package com.borjadelgadodev.freetogame.ui.screens.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.borjadelgadodev.freetogame.App
import com.borjadelgadodev.freetogame.framework.GamesRoomDataSource
import com.borjadelgadodev.freetogame.framework.GamesServerDataSource
import com.borjadelgadodev.freetogame.framework.remote.GamesClient
import com.borjadelgadodev.freetogame.ui.screens.detail.DetailScreen
import com.borjadelgadodev.freetogame.ui.screens.detail.DetailViewModel
import com.borjadelgadodev.freetogame.ui.screens.home.HomeScreen
import com.borjadelgadodev.freetogame.ui.screens.home.HomeViewModel
import com.borjadelgadodev.freetogame.ui.screens.navigation.ScreenDestination.Constants.NavArgs
import com.borjadelgadodev.usecases.FetchGamesUseCase
import com.borjadelgadodev.usecases.FindGameByIdUseCase
import com.borjadelgadodev.usecases.ToggleFavoriteUseCase

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val app = LocalContext.current.applicationContext as App
    val gamesRepository = com.borjadelgadodev.data.GamesRepository(
        GamesRoomDataSource(app.db.gamesDao()),
        GamesServerDataSource(GamesClient.instance)
    )

    Scaffold(bottomBar = { BottomNavigationBar(navController) }) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = ScreenDestination.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(ScreenDestination.Home.route) {
                HomeScreen(
                    onClick = { game ->
                        navController.navigate(ScreenDestination.Detail.createDetailRoute(game.id))
                    },
                    viewModel = viewModel { HomeViewModel(FetchGamesUseCase(gamesRepository)) },
                )
            }
            composable(ScreenDestination.MyGames.route) {
                // Implementacion mas adelante
            }
            composable(ScreenDestination.MyProfile.route) {
                // Implementacion mas adelante
            }
            composable(
                route = ScreenDestination.Detail.route,
                arguments = listOf(navArgument(NavArgs.GameId.key) { type = NavType.IntType })
            ) { backStackEntry ->
                val gameId = requireNotNull(backStackEntry.arguments?.getInt(NavArgs.GameId.key))
                DetailScreen(
                    viewModel = viewModel {
                        DetailViewModel(
                            gameId,
                            FindGameByIdUseCase(gamesRepository),
                            ToggleFavoriteUseCase(gamesRepository)
                        )
                    },
                    onBackClick = { navController.popBackStack() })
            }
        }
    }
}
