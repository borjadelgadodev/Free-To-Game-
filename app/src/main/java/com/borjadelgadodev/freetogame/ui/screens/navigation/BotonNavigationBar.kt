package com.borjadelgadodev.freetogame.ui.screens.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

        val destinations = listOf(
            ScreenDestination.Home,
            ScreenDestination.MyGames,
            ScreenDestination.MyProfile
        )

        destinations.forEach { destination ->
            NavigationBarItem(
                icon = {
                    destination.icon?.let {
                        Icon(
                            it,
                            contentDescription = stringResource(destination.labelRes)
                        )
                    }
                },
                label = { Text(stringResource(destination.labelRes)) },
                selected = currentRoute == destination.route,
                onClick = { destination.navigate(navController) }
            )
        }
    }
}
