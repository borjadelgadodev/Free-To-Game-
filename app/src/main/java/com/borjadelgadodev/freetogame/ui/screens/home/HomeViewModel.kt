package com.borjadelgadodev.freetogame.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borjadelgadodev.freetogame.data.Game
import com.borjadelgadodev.freetogame.data.GamesRepository
import com.borjadelgadodev.freetogame.data.GamesRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: GamesRepository = GamesRepositoryImpl()) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state get() = _state.asStateFlow()

    fun onUiReady() {
        viewModelScope.launch {
            val games = repository.getGames()
            _state.value = UiState(loading = true)
            _state.value = UiState(loading = false, games)
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val games: List<Game> = emptyList()
    )
}
