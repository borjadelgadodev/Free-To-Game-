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
import com.borjadelgadodev.freetogame.data.GamesRepository
import com.borjadelgadodev.freetogame.data.datasource.GamesLocalDataSource
import com.borjadelgadodev.freetogame.data.datasource.remote.GamesRemoteDataSource
import com.borjadelgadodev.freetogame.ui.screens.detail.DetailScreen
import com.borjadelgadodev.freetogame.ui.screens.detail.DetailViewModel
import com.borjadelgadodev.freetogame.ui.screens.home.HomeScreen
import com.borjadelgadodev.freetogame.ui.screens.home.HomeViewModel
import com.borjadelgadodev.freetogame.ui.screens.navigation.ScreenDestination.Constants.NavArgs

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val app = LocalContext.current.applicationContext as App
    val gamesRepository = GamesRepository(
        GamesLocalDataSource(app.db.gamesDao()),
        GamesRemoteDataSource()
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
                    viewModel = viewModel { HomeViewModel(gamesRepository) },
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
                    viewModel = viewModel { DetailViewModel(gameId, gamesRepository) },
                    onBackClick = { navController.popBackStack() })
            }
        }
    }
}
