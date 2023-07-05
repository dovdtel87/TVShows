package com.example.tvshows.data.network.model

import com.google.gson.annotations.SerializedName

data class ShowDto(
    val id: String,
    val name: String,
    @SerializedName("backdrop_path") val path: String
)
