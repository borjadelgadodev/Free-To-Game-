package com.borjadelgadodev.freetogame.ui.screens.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

enum class ScreenDestination(val route: String, val label: String, val icon: ImageVector) {
    Home("home", "Inicio", Icons.Default.Home),
    Games("games", "Juegos", Icons.Default.Add),
    MyGames("my_games", "Mis Juegos", Icons.Default.Favorite),
    MyProfile("my_profile", "Perfil", Icons.Default.Person);
    
    fun navigate(navController: NavController) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}