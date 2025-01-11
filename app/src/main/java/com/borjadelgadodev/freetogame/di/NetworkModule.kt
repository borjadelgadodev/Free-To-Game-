package com.borjadelgadodev.freetogame.di

import com.borjadelgadodev.freetogame.framework.remote.GamesClient
import org.koin.dsl.module

val networkModule = module {
    single { GamesClient.instance }
}
