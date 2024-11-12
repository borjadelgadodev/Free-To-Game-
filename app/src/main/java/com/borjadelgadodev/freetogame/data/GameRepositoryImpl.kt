package com.borjadelgadodev.freetogame.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GameRepositoryImpl : GameRepository {
    override suspend fun getGameById(id: Int): Game {
        return withContext(Dispatchers.IO) {
            GamesClient.instance.getGameById(id)
        }
    }
}