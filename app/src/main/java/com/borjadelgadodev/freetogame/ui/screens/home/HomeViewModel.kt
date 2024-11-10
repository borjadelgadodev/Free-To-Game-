package com.borjadelgadodev.freetogame.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borjadelgadodev.freetogame.data.Game
import com.borjadelgadodev.freetogame.data.GamesRepository
import com.borjadelgadodev.freetogame.data.GamesRepositoryImpl
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: GamesRepository = GamesRepositoryImpl()) : ViewModel() {
    var state by mutableStateOf(UiState())
        private set

    fun onUiReady() {
        viewModelScope.launch {
            state = UiState(loading = true)
            try {
                val games = repository.getGames()
                state = UiState(loading = false, games = games)
            } catch (e: Exception) {
                state = UiState(loading = false, games = emptyList())
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val games: List<Game> = emptyList()
    )
}