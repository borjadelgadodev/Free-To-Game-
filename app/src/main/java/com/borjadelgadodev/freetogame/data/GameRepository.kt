package com.borjadelgadodev.freetogame.data

interface GameRepository {
    suspend fun getGameById(id: Int): Game
}
