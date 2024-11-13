package com.borjadelgadodev.freetogame.data

import com.borjadelgadodev.freetogame.utils.Constants.GAMES_ENDPOINT
import com.borjadelgadodev.freetogame.utils.Constants.GAME_ID_ENDPOINT
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesApi {
    @GET(GAME_ID_ENDPOINT)
    suspend fun getGameById(@Query("id") gameId: Int): Game

    @GET(GAMES_ENDPOINT)
    suspend fun getGames(): List<Game>
}
