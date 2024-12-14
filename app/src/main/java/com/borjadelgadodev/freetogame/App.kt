package com.borjadelgadodev.freetogame

import android.app.Application
import androidx.room.Room
import com.borjadelgadodev.freetogame.data.datasource.database.GamesDatabase

class App : Application() {
    lateinit var db: GamesDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, GamesDatabase::class.java, "games-database")
            .fallbackToDestructiveMigration()
            .build()
    }
}
