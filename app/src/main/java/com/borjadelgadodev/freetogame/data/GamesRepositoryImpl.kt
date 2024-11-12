package com.borjadelgadodev.freetogame.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GamesRepositoryImpl : GamesRepository {
    override suspend fun getGames(): List<Game> {
        return withContext(Dispatchers.IO) {
            GamesClient.instance.getGames()
        }
    }
}
