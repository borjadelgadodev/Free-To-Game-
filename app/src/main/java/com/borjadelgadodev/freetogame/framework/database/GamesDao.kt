package com.borjadelgadodev.freetogame.framework.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GamesDao {

    @Query("SELECT * FROM DbGame")
    fun getGames(): Flow<List<DbGame>>

    @Query("SELECT * FROM DbGame WHERE id = :gameId")
    fun getGameById(gameId: Int): Flow<DbGame?>

    @Query("SELECT COUNT(*) FROM DbGame")
    suspend fun countGames(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveGames(games: List<DbGame>)
}
