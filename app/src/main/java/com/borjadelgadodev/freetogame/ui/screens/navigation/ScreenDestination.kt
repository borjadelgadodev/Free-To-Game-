package com.borjadelgadodev.freetogame.ui.screens.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.borjadelgadodev.freetogame.R

sealed class ScreenDestination(
    val route: String,
    val labelRes: Int,
    val icon: ImageVector? = null
) {
    data object Home : ScreenDestination(Constants.HOME_ROUTE, R.string.home, Icons.Default.Home)
    data object MyGames :
        ScreenDestination(Constants.MY_GAMES_ROUTE, R.string.my_games, Icons.Default.Favorite)

    data object MyProfile :
        ScreenDestination(Constants.MY_PROFILE_ROUTE, R.string.profile, Icons.Default.Person)

    data object Detail : ScreenDestination(Constants.DETAIL_ROUTE_WITH_ARG, R.string.detail) {
        fun createDetailRoute(gameId: Int): String = "${Constants.DETAIL_ROUTE}/$gameId"
    }

    fun navigate(navController: NavController) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    internal object Constants {
        const val HOME_ROUTE = "home"
        const val MY_GAMES_ROUTE = "my_games"
        const val MY_PROFILE_ROUTE = "my_profile"
        const val DETAIL_ROUTE = "detail"
        val DETAIL_ROUTE_WITH_ARG = "detail/{${NavArgs.GameId.key}}"


        enum class NavArgs(val key: String) {
            GameId("gameId")
        }
    }
}
