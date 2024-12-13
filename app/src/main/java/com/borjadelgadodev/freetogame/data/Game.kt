package com.borjadelgadodev.freetogame.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Game(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String? = null,
    val description: String? = null,
    val thumbnail: String? = null,
    val genre: String? = null,
    val platform: String? = null,
    val developer: String? = null,
    val isFavorite: Boolean
)
