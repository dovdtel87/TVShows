package com.example.tvshows.repository

import com.example.tvshows.data.model.Show
import com.example.tvshows.data.repository.ShowsRepository

class FakeShowsRepository : ShowsRepository {
    override suspend fun fetchShows(): Result<List<Show>> {
        val show1 = Show(1,"Batman", "aUrl1")
        val show2 = Show(2,"Superman", "aUrl2")
        val show3 = Show(3,"Friends", "aUrl3")
        return Result.success(listOf(show1, show2, show3))
    }
}
