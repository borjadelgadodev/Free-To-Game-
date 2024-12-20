package com.borjadelgadodev.data.datasource

import com.borjadelgadodev.domain.Game

interface GamesRemoteDataSource {
    suspend fun getGames(): List<Game>
    suspend fun getGameById(gameId: Int): Game
}

