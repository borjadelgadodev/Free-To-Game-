package com.borjadelgadodev.freetogame.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DbGame::class], version = 1, exportSchema = false)
abstract class GamesDatabase : RoomDatabase() {
    abstract fun gamesDao(): GamesDao
}
