package com.borjadelgadodev.freetogame.di

import com.borjadelgadodev.data.GamesRepository
import com.borjadelgadodev.data.datasource.GamesLocalDataSource
import com.borjadelgadodev.data.datasource.GamesRemoteDataSource
import com.borjadelgadodev.freetogame.framework.GamesRoomDataSource
import com.borjadelgadodev.freetogame.framework.GamesServerDataSource
import org.koin.dsl.module

val dataModule = module {
    single<GamesLocalDataSource> { GamesRoomDataSource(get()) }
    single<GamesRemoteDataSource> { GamesServerDataSource(get()) }
    single { GamesRepository(get(), get()) }
}
