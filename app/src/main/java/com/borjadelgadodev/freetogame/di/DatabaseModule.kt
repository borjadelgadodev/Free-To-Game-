package com.borjadelgadodev.freetogame.di

import com.borjadelgadodev.freetogame.App
import org.koin.dsl.module

val databaseModule = module {
    single { get<App>().db.gamesDao() }
}
