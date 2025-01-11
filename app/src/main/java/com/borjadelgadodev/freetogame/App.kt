package com.borjadelgadodev.freetogame

import android.app.Application
import androidx.room.Room
import com.borjadelgadodev.freetogame.di.appModule
import com.borjadelgadodev.freetogame.di.dataModule
import com.borjadelgadodev.freetogame.di.databaseModule
import com.borjadelgadodev.freetogame.di.networkModule
import com.borjadelgadodev.freetogame.di.useCaseModule
import com.borjadelgadodev.freetogame.di.viewModelModule
import com.borjadelgadodev.freetogame.framework.database.GamesDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    lateinit var db: GamesDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(this, GamesDatabase::class.java, "games-database")
            .fallbackToDestructiveMigration()
            .build()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(appModule, databaseModule, dataModule, networkModule, useCaseModule, viewModelModule))
        }
    }
}

