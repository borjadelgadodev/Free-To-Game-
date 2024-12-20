package com.borjadelgadodev.usecases

import com.borjadelgadodev.data.GamesRepository
import com.borjadelgadodev.domain.Game

class ToggleFavoriteUseCase(private val repository: GamesRepository) {
    suspend operator fun invoke(game: Game) = repository.toggleFavorite(game)
}