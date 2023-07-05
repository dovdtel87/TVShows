package com.example.tvshows.data.network.api

import com.example.tvshows.data.network.model.ShowsResponseDto
import retrofit2.http.GET

interface ShowsApi {

    @GET("tv/top_rated?api_key=25a8f80ba018b52efb64f05140f6b43c&langu%20age=en-US")
    suspend fun fetchShows() : ShowsResponseDto
}
