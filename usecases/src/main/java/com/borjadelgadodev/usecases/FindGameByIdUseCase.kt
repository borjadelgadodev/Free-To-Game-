package com.borjadelgadodev.usecases

import com.borjadelgadodev.data.GamesRepository
import com.borjadelgadodev.domain.Game
import kotlinx.coroutines.flow.Flow

class FindGameByIdUseCase(private val repository: GamesRepository) {
    operator fun invoke(gameId: Int): Flow<Game?> = repository.getGameById(gameId)
}