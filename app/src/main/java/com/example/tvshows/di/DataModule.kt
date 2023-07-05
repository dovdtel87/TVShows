package com.example.tvshows.di

import com.example.tvshows.data.repository.ShowsRepository
import com.example.tvshows.tvshows.domain.repository.ShowsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindShowsRepository(repository: ShowsRepositoryImpl): ShowsRepository
}
