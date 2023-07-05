package com.example.tvshows.di

import android.content.Context
import androidx.room.Room
import com.example.tvshows.data.database.ShowDao
import com.example.tvshows.data.database.ShowDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): ShowDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ShowDatabase::class.java,
            "Shows.db"
        ).build()
    }

    @Provides
    fun provideShowsDao(database: ShowDatabase): ShowDao = database.showDao()
}
