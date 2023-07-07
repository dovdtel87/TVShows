package com.example.tvshows.shows.domain.repository

import com.example.tvshows.data.database.ShowDao
import com.example.tvshows.data.database.ShowEntity
import com.example.tvshows.data.mapper.ShowsMapper
import com.example.tvshows.data.network.api.ShowsApi
import com.example.tvshows.data.network.model.ShowDto
import com.example.tvshows.data.network.model.ShowsResponseDto
import com.example.tvshows.tvshows.domain.repository.ShowsRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ShowsRepositoryImplTest {
    @MockK
    private lateinit var showsApi: ShowsApi
    @MockK
    private lateinit var showsDao: ShowDao

    private lateinit var showsRepositoryImpl: ShowsRepositoryImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        showsRepositoryImpl = ShowsRepositoryImpl(showsApi, ShowsMapper(), showsDao)
    }

    @Test
    fun `when fetchShows is called and there is a list in the cache it returns that list`() = runBlocking {

        val showsEntityList = listOf(ShowEntity("1", "Batman", "/aUrl1"), ShowEntity("2", "Superman", "/aUrl2"), ShowEntity("3", "Friends", "/aUrl3"))
        coEvery { showsDao.getShows() } returns showsEntityList

        val response = showsRepositoryImpl.fetchShows()

        coVerify(exactly = 0) { showsApi.fetchShows() }

        val data = response.getOrDefault(null)
        TestCase.assertEquals("Batman", data?.get(0)?.name)
    }

    @Test
    fun `when fetchShows is called and there is no list in the cache it calls the api to fetch it`() = runBlocking {
        val showsList = listOf(ShowDto("1", "Batman", "/aUrl1"), ShowDto("2", "Superman", "/aUrl2"), ShowDto("3", "Friends", "/aUrl3"))

        coEvery { showsDao.getShows() } returns emptyList()
        coEvery { showsApi.fetchShows() } returns ShowsResponseDto(1,10, showsList)
        coEvery { showsDao.insertShows(any()) } returns Unit

        val response = showsRepositoryImpl.fetchShows()

        coVerify(exactly = 1) { showsApi.fetchShows() }

        val data = response.getOrDefault(null)
        TestCase.assertEquals("Batman", data?.get(0)?.name)
    }
}
