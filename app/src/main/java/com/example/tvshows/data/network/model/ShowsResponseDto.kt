package com.example.tvshows.data.network.model

import com.google.gson.annotations.SerializedName

data class ShowsResponseDto(
    val page: Int,
    val totalPages: Int,
    @SerializedName("results") val list: List<ShowDto>
)
