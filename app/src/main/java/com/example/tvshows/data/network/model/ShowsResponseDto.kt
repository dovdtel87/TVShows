package com.example.tvshows.data.network.model

data class ShowsResponseDto(
    val page: Int,
    val totalPages: Int,
    val list: List<ShowDto>
)
