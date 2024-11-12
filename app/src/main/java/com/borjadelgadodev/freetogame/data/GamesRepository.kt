package com.borjadelgadodev.freetogame.data

interface GamesRepository {
    suspend fun getGames(): List<Game>
}
