package com.borjadelgadodev.freetogame.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.borjadelgadodev.freetogame.data.Game
import kotlinx.coroutines.flow.Flow

@Dao
interface GamesDao {

    @Query("SELECT * FROM Game")
    fun getGames(): Flow<List<Game>>

    @Query("SELECT * FROM Game WHERE id = :gameId")
    fun getGameById(gameId: Int): Flow<Game?>

    @Query("SELECT COUNT(*) FROM Game")
    suspend fun countGames(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveGames(games: List<Game>)
}
