package com.borjadelgadodev.freetogame.di

import com.borjadelgadodev.freetogame.ui.screens.detail.DetailViewModel
import com.borjadelgadodev.freetogame.ui.screens.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { (gameId: Int) ->
        DetailViewModel(gameId, get(), get())
    }
}
