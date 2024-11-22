package com.borjadelgadodev.freetogame.data

data class Game(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: String,
    val genre: String,
    val platform: String,
    val developer: String,
)
