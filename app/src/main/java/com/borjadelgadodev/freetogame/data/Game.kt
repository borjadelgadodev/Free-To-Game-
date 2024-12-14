package com.borjadelgadodev.freetogame.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Game(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String? = null,
    val thumbnail: String,
    val genre: String,
    val platform: String,
    val developer: String,
    val isFavorite: Boolean
)
