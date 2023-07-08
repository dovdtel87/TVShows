package com.example.tvshows.data.network.model

import com.google.gson.annotations.SerializedName

data class ShowDto(
    val id: Int,
    val name: String,
    @SerializedName("backdrop_path") val path: String
)
