package com.example.tvshows.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shows")
data class ShowEntity(
    @PrimaryKey val id: String,
    val name: String,
    val path: String
)
