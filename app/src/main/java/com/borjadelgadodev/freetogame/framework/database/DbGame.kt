package com.borjadelgadodev.freetogame.framework.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbGame(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String? = null,
    val thumbnail: String,
    val genre: String,
    val platform: String,
    val developer: String,
    val isFavorite: Boolean,
    @Embedded val minimumSystemRequirements: DbMinimumSystemRequirements?,
)
