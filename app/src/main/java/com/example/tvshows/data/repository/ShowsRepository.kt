package com.example.tvshows.data.repository

import com.example.tvshows.data.model.Show

interface ShowsRepository {
    suspend fun fetchShows() :  Result<List<Show>>
}
