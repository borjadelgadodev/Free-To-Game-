package com.borjadelgadodev.freetogame.ui.screens.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borjadelgadodev.freetogame.R
import com.borjadelgadodev.freetogame.data.Game
import com.borjadelgadodev.freetogame.data.GameRepository
import com.borjadelgadodev.freetogame.data.GameRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val context: Context,
    private val gameId: Int,
    private val repository: GameRepository = GameRepositoryImpl()
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> get() = _state.asStateFlow()

    init {
        loadGameDetails()
    }

    private fun loadGameDetails() {
        viewModelScope.launch {
            val game = repository.getGameById(gameId)
            _state.value = UiState(loading = true)
            _state.value = UiState(loading = false, game = game)
        }
    }

    fun onAction(action: DetailAction) {
        when (action) {
            is DetailAction.FavoriteClick -> onFavoriteClick()
            is DetailAction.MessageShown -> onMessageShown()
        }
    }

    private fun onFavoriteClick() {
        _state.update {
            it.copy(message = context.getString(R.string.game_added_to_favorites))
        }
    }

    private fun onMessageShown() {
        _state.update {
            it.copy(message = null)
        }
    }

    data class UiState(
        val loading: Boolean = false, val game: Game? = null, val message: String? = null
    )
}

sealed class DetailAction {
    data object FavoriteClick : DetailAction()
    data object MessageShown : DetailAction()
}
