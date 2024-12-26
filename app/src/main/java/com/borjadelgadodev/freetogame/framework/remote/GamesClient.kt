package com.borjadelgadodev.freetogame.framework.remote

import com.borjadelgadodev.freetogame.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GamesClient {
    private const val BASE_URL = Constants.BASE_URL

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val instance: GamesApi = retrofit.create(GamesApi::class.java)
}