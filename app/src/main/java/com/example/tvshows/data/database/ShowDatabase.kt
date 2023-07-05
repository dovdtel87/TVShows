package com.example.tvshows.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ShowEntity::class], version = 1)
abstract class ShowDatabase : RoomDatabase() {
    abstract fun showDao(): ShowDao

    companion object {
        private const val DATABASE_NAME = "show_database"

        fun build(context: Context): ShowDatabase {
            return Room.databaseBuilder(context, ShowDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}
