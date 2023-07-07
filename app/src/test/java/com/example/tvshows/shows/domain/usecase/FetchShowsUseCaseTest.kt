package com.example.tvshows.shows.domain.usecase

import com.example.tvshows.data.model.Show
import com.example.tvshows.data.repository.ShowsRepository
import com.example.tvshows.tvshows.domain.usecases.FetchShowsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FetchShowsUseCaseTest {

    @MockK
    private lateinit var showsRepository: ShowsRepository

    private lateinit var useCase: FetchShowsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = FetchShowsUseCase(showsRepository)
    }

    @Test
    fun `invoke should return success result with fetched shows`() = runBlocking {
        val expectedShows = listOf(
            Show("1", "Show 1", "/aUrl1"),
            Show("2", "Show 2", "/aUrl2"),
            Show("3", "Show 3", "/aUrl3")
        )

        coEvery { showsRepository.fetchShows() } returns Result.success(expectedShows)
        val result = useCase.invoke()
        assertEquals(Result.success(expectedShows), result)
    }

    @Test
    fun `invoke should return failure result when an exception is thrown`() = runBlocking {
        val expectedException = Exception("Failed to fetch shows")
        coEvery { showsRepository.fetchShows() } throws expectedException
        val fetchShowsUseCase = FetchShowsUseCase(showsRepository)
        val result = fetchShowsUseCase.invoke()

        assertEquals(Result.failure<List<Show>>(expectedException), result)
    }
}
