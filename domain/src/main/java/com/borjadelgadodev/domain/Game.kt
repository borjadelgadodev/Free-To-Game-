package com.borjadelgadodev.domain

data class Game(
    val id: Int,
    val title: String,
    val description: String? = null,
    val thumbnail: String,
    val genre: String,
    val platform: String,
    val developer: String,
    val isFavorite: Boolean,
    val minimumSystemRequirements: MinimumSystemRequirements?
)
