package com.borjadelgadodev.freetogame.data

import com.borjadelgadodev.freetogame.data.datasource.GamesLocalDataSource
import com.borjadelgadodev.freetogame.data.datasource.remote.GamesRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.transform

class GamesRepository(
    private val localDataSource: GamesLocalDataSource,
    private val remoteDataSource: GamesRemoteDataSource
) {
    val games: Flow<List<Game>> = localDataSource.games
        .onEach { localGames ->
            if (localGames.isEmpty()) {
                val remoteGames = remoteDataSource.getGames()
                localDataSource.saveGames(remoteGames)
            }
        }
        .transform { localGames ->
            emit(localGames)
            if (localGames.isEmpty()) {
                val remoteGames = remoteDataSource.getGames()
                localDataSource.saveGames(remoteGames)
                emit(remoteGames)
            }
        }

    fun getGameById(gameId: Int): Flow<Game?> = localDataSource.getGameById(gameId)
        .onEach {
            if (it == null) {
                val remoteGame = remoteDataSource.getGameById(gameId)
                localDataSource.saveGames(listOf(remoteGame))
            }
        }
        .filterNotNull()

    suspend fun toggleFavorite(game: Game) {
        localDataSource.saveGames(listOf(game.copy(isFavorite = !game.isFavorite)))
    }
}
