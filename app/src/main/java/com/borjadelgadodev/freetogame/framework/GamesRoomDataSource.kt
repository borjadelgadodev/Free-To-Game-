package com.borjadelgadodev.freetogame.framework

import com.borjadelgadodev.data.datasource.GamesLocalDataSource
import com.borjadelgadodev.domain.Game
import com.borjadelgadodev.domain.MinimumSystemRequirements
import com.borjadelgadodev.freetogame.framework.database.DbGame
import com.borjadelgadodev.freetogame.framework.database.DbMinimumSystemRequirements
import com.borjadelgadodev.freetogame.framework.database.GamesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GamesRoomDataSource(private val gamesDao: GamesDao) :
    GamesLocalDataSource {

    override val games: Flow<List<Game>> =
        gamesDao.getGames().map { games ->
            games.map { dbGame -> dbGame.toDomainGame() }
        }

    override fun getGameById(gameId: Int): Flow<Game?> =
        gamesDao.getGameById(gameId).map {
            it?.toDomainGame()
        }

    override suspend fun saveGames(games: List<Game>) =
        gamesDao.saveGames(games.map { it.toDbGame() })
}

private fun DbGame.toDomainGame(): Game = Game(
    id = id,
    title = title,
    thumbnail = thumbnail,
    description = description,
    genre = genre,
    platform = platform,
    developer = developer,
    isFavorite = isFavorite,
    minimumSystemRequirements = minimumSystemRequirements?.toDomain()
)

private fun DbMinimumSystemRequirements.toDomain(): MinimumSystemRequirements =
    MinimumSystemRequirements(
        graphics = graphics,
        memory = memory,
        os = os,
        processor = processor,
        storage = storage
    )

private fun Game.toDbGame(): DbGame = DbGame(
    id = id,
    title = title,
    thumbnail = thumbnail,
    description = description,
    genre = genre,
    platform = platform,
    developer = developer,
    isFavorite = isFavorite,
    minimumSystemRequirements = minimumSystemRequirements?.toDb()
)

private fun MinimumSystemRequirements.toDb(): DbMinimumSystemRequirements =
    DbMinimumSystemRequirements(
        graphics = graphics,
        memory = memory,
        os = os,
        processor = processor,
        storage = storage
    )