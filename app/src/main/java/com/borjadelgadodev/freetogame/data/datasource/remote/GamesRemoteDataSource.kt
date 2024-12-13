package com.borjadelgadodev.freetogame.data.datasource.remote

import com.borjadelgadodev.freetogame.data.Game
import com.borjadelgadodev.freetogame.data.GamesClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GamesRemoteDataSource {

    suspend fun getGames(): List<Game> {
        return withContext(Dispatchers.IO) {
            GamesClient.instance.getGames()
        }
    }

    suspend fun getGameById(gameId: Int): Game {
        return withContext(Dispatchers.IO) {
            GamesClient.instance.getGameById(gameId)
        }
    }
}
