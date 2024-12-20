package com.borjadelgadodev.usecases

import com.borjadelgadodev.data.GamesRepository
import com.borjadelgadodev.domain.Game
import kotlinx.coroutines.flow.Flow

class FetchGamesUseCase (private val repository: GamesRepository) {
    operator fun invoke(): Flow<List<Game>> = repository.games
}