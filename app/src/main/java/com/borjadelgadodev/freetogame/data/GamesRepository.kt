package com.borjadelgadodev.freetogame.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GamesRepository {
    suspend fun getGames() :List<Game>{
        return withContext(Dispatchers.IO){
            GamesClient.instance.getGames()
        }
    }

    suspend fun getGameById(id: Int) :Game{
        return withContext(Dispatchers.IO){
            GamesClient.instance.getGameById(id)
        }
    }
}