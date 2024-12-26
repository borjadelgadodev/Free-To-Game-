package com.borjadelgadodev.data.datasource

import com.borjadelgadodev.domain.Game
import kotlinx.coroutines.flow.Flow

interface GamesLocalDataSource {
    val games: Flow<List<Game>>
    fun getGameById(gameId: Int): Flow<Game?>
    suspend fun saveGames(games: List<Game>)
}
