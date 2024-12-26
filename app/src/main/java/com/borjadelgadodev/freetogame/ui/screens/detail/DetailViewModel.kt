package com.borjadelgadodev.freetogame.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borjadelgadodev.domain.Game
import com.borjadelgadodev.freetogame.Result
import com.borjadelgadodev.freetogame.ifSuccess
import com.borjadelgadodev.freetogame.stateAsResultIn
import com.borjadelgadodev.usecases.FindGameByIdUseCase
import com.borjadelgadodev.usecases.ToggleFavoriteUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    gameId: Int,
    private val findGameByIdUseCase: FindGameByIdUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
) : ViewModel() {

    val state: StateFlow<Result<Game?>> = findGameByIdUseCase(gameId)
        .stateAsResultIn(viewModelScope)

    fun onFavoriteClick() {
        state.value.ifSuccess { game ->
            viewModelScope.launch {
                if (game != null) {
                    toggleFavoriteUseCase(game)

                }
            }
        }
    }
}
