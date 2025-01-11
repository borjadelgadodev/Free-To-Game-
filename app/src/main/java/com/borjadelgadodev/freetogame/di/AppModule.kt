package com.borjadelgadodev.freetogame.di

import com.borjadelgadodev.freetogame.App
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { androidContext() as App }
}