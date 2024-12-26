package com.borjadelgadodev.freetogame.framework

import com.borjadelgadodev.data.datasource.GamesRemoteDataSource
import com.borjadelgadodev.domain.Game
import com.borjadelgadodev.freetogame.framework.remote.GamesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class GamesServerDataSource(
    private val gamesApi: GamesApi
) : GamesRemoteDataSource {

    override suspend fun getGames(): List<Game> {
        return withContext(Dispatchers.IO) {
            gamesApi.getGames()
        }
    }

    override suspend fun getGameById(gameId: Int): Game {
        return withContext(Dispatchers.IO) {
            gamesApi.getGameById(gameId)
        }
    }
}