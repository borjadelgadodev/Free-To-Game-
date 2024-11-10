package com.borjadelgadodev.freetogame.ui.screens.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borjadelgadodev.freetogame.data.Game
import com.borjadelgadodev.freetogame.data.GamesRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val gameId: Int, private val repository: GamesRepository = GamesRepository()) : ViewModel() {
    var state by mutableStateOf(UiState())
        private set

    init {
        loadGameDetails()
    }

    private fun loadGameDetails() {
        viewModelScope.launch {
            state = UiState(loading = true)
            try {
                val game = repository.getGameById(gameId)
                state = UiState(loading = false, game = game)
            } catch (e: Exception) {
                state = UiState(loading = false, game = null)
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val game: Game? = null
    )
}