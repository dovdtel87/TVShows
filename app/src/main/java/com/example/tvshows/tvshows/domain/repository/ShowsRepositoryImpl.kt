package com.example.tvshows.tvshows.domain.repository

import com.example.tvshows.data.model.Show
import com.example.tvshows.data.repository.ShowsRepository

class ShowsRepositoryImpl : ShowsRepository {
    override suspend fun fetchShows(): Result<List<Show>> {
        TODO("Not yet implemented")
    }
}
