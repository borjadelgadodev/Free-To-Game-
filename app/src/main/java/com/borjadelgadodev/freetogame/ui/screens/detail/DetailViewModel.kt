package com.borjadelgadodev.freetogame.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borjadelgadodev.freetogame.Result
import com.borjadelgadodev.freetogame.data.Game
import com.borjadelgadodev.freetogame.data.GamesRepository
import com.borjadelgadodev.freetogame.ifSuccess
import com.borjadelgadodev.freetogame.stateAsResultIn
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val gameId: Int, private val repository: GamesRepository) : ViewModel() {

    val state: StateFlow<Result<Game?>> = repository.getGameById(gameId)
        .stateAsResultIn(viewModelScope)

    fun onFavoriteClick() {
        state.value.ifSuccess { game ->
            viewModelScope.launch {
                if (game != null) {
                    repository.toggleFavorite(game)

                }
            }
        }
    }
}
