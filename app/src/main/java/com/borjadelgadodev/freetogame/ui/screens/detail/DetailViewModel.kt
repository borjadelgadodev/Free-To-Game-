package com.borjadelgadodev.freetogame.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borjadelgadodev.freetogame.data.Game
import com.borjadelgadodev.freetogame.data.GameRepository
import com.borjadelgadodev.freetogame.data.GameRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(private val gameId: Int, private val repository: GameRepository = GameRepositoryImpl()) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> get() = _state.asStateFlow()

    init {
        loadGameDetails()
    }

    private fun loadGameDetails() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            val game = repository.getGameById(gameId)
            _state.value = UiState(loading = false, game = game)
        }
    }

    fun onAction(action: DetailAction, message: String? = null) {
        when (action) {
            is DetailAction.FavoriteClick -> onFavoriteClick(message)
            is DetailAction.MessageShown -> onMessageShown()
        }
    }

    private fun onFavoriteClick(message: String?) {
        _state.update {
            it.copy(message = message)
        }
    }

    private fun onMessageShown() {
        _state.update {
            it.copy(message = null)
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val game: Game? = null,
        val message: String? = null
    )
}

sealed class DetailAction {
    data object FavoriteClick : DetailAction()
    data object MessageShown : DetailAction()
}
