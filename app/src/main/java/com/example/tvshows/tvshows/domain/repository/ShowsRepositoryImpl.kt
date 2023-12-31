package com.example.tvshows.tvshows.domain.repository

import com.example.tvshows.data.database.ShowDao
import com.example.tvshows.data.mapper.ShowsMapper
import com.example.tvshows.data.model.Show
import com.example.tvshows.data.network.api.ShowsApi
import com.example.tvshows.data.repository.ShowsRepository
import javax.inject.Inject

class ShowsRepositoryImpl @Inject constructor(
    private val showsApi: ShowsApi,
    private val mapper: ShowsMapper,
    private val showDao: ShowDao
) : ShowsRepository {
    override suspend fun fetchShows(): Result<List<Show>> {
        return try {
            val cachedShows = mapper.mapToShowFromEntity(showDao.getShows())
            if (cachedShows.isNotEmpty()) {
                Result.success(cachedShows)
            } else {
                val apiShows = showsApi.fetchShows().list
                showDao.insertShows(mapper.mapToShowEntity(apiShows))
                val mappedShows = mapper.map(apiShows)
                Result.success(mappedShows)
            }
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
