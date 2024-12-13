package com.borjadelgadodev.freetogame.data.datasource

import com.borjadelgadodev.freetogame.data.Game
import kotlinx.coroutines.flow.Flow

class GamesLocalDataSource(private val gamesDao: GamesDao) {

    val games: Flow<List<Game>> = gamesDao.getGames()

    fun getGameById(gameId: Int): Flow<Game?> = gamesDao.getGameById(gameId)

    suspend fun saveGames(games: List<Game>) = gamesDao.saveGames(games)
}