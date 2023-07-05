package com.example.tvshows.data.network.api

import com.example.tvshows.BuildConfig
import com.example.tvshows.data.network.model.ShowsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ShowsApi {

    companion object {
        const val DEFAULT_LANGUAGE = "en-US"
    }
    @GET("tv/top_rated")
    suspend fun fetchShows(
        @Query("language") language: String = DEFAULT_LANGUAGE,
        @Query("api_key") key: String = BuildConfig.API_KEY
    ) : ShowsResponseDto
}
