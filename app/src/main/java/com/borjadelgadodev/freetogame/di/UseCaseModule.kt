package com.borjadelgadodev.freetogame.di

import com.borjadelgadodev.usecases.FetchGamesUseCase
import com.borjadelgadodev.usecases.FindGameByIdUseCase
import com.borjadelgadodev.usecases.ToggleFavoriteUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { FetchGamesUseCase(get()) }
    factory { FindGameByIdUseCase(get()) }
    factory { ToggleFavoriteUseCase(get()) }
}
