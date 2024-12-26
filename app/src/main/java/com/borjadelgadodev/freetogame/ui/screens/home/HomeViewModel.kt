package com.borjadelgadodev.freetogame.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borjadelgadodev.domain.Game
import com.borjadelgadodev.freetogame.Result
import com.borjadelgadodev.usecases.FetchGamesUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val fetchGamesUseCase: FetchGamesUseCase) : ViewModel() {

    private val _state = MutableStateFlow<Result<List<Game>>>(Result.Loading)
    val state get() = _state.asStateFlow()

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = Result.Loading
            try {
                fetchGamesUseCase.invoke().collect {
                    _state.value = Result.Success(it)
                }
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                _state.value = Result.Error(e)
            }
        }
    }
}
