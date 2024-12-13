package com.borjadelgadodev.freetogame.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.borjadelgadodev.freetogame.data.Game
import com.borjadelgadodev.freetogame.data.datasource.GamesDao

@Database(entities = [Game::class], version = 1, exportSchema = false)
abstract class GamesDatabase : RoomDatabase() {
    abstract fun gamesDao(): GamesDao
}
