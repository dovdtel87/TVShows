package com.example.tvshows.data.mapper

import com.example.tvshows.data.database.ShowEntity
import com.example.tvshows.data.model.Show
import com.example.tvshows.data.network.model.ShowDto
import javax.inject.Inject

class ShowsMapper @Inject constructor() {

    companion object {
        const val IMAGE_URL_PREFIX = "https://image.tmdb.org/t/p/w500/"
    }

    fun map(showsDto: List<ShowDto>): List<Show> {
        val showsList = mutableListOf<Show>()
        showsDto.forEach {
            showsList.add(
                Show(
                    id = it.id,
                    name = it.name,
                    url = it.path.generateUrl()
                )
            )
        }
        return showsList
    }

    private fun String.generateUrl(): String = IMAGE_URL_PREFIX + this

    fun mapToShowEntity(showsDto: List<ShowDto>) : List<ShowEntity> {
        val showListEntity = mutableListOf<ShowEntity>()
        showsDto.forEach{
            showListEntity.add(
                ShowEntity(
                    id =  it.id,
                    name = it.name,
                    path = it.path
                )
            )
        }
        return showListEntity
    }

    fun mapToShowFromEntity(showsDto: List<ShowEntity>) : List<Show> {
        val showList = mutableListOf<Show>()
        showsDto.forEach{
            showList.add(
                Show(
                    id =  it.id,
                    name = it.name,
                    url = it.path.generateUrl()
                )
            )
        }
        return showList
    }
}
