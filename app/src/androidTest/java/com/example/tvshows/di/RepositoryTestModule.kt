package com.example.tvshows.di

import com.example.tvshows.data.repository.ShowsRepository
import com.example.tvshows.repository.FakeShowsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
object RepositoryTestModule {

    @Singleton
    @Provides
    fun provideShowsRepository(): ShowsRepository {
        return FakeShowsRepository()
    }
}
