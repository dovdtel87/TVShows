package com.example.tvshows.shows.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.tvshows.data.model.Show
import com.example.tvshows.tvshows.domain.usecases.FetchShowsUseCase
import com.example.tvshows.tvshows.ui.ListShowsViewModel
import com.example.tvshows.tvshows.ui.state.ListScreenState
import com.example.tvshows.R
import com.example.tvshows.tvshows.ui.model.ShowUI
import com.example.tvshows.util.MainCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ListShowsViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var fetchShowsUseCase: FetchShowsUseCase

    private lateinit var viewModel: ListShowsViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = ListShowsViewModel(fetchShowsUseCase)
    }

    @Test
    fun `fetchShows should update state to Content when shows are fetched successfully`() = runTest {
        val expectedShows = listOf(
            Show("1", "Show 1", "/aUrl1"),
            Show("2", "Show 2", "/aUrl2"),
            Show("3", "Show 3", "/aUrl3")
        )

        val expectedShowsUI = listOf(
            ShowUI("1", "Show 1", "/aUrl1"),
            ShowUI("2", "Show 2", "/aUrl2"),
            ShowUI("3", "Show 3", "/aUrl3")
        )

        coEvery { fetchShowsUseCase.invoke() } returns Result.success(expectedShows)

        viewModel.fetchShows()

        assertEquals(ListScreenState.Content(expectedShowsUI), viewModel.state.value)
    }

    @Test
    fun `fetchShows should update state to Error when an exception occurs during fetching`() = runTest {
        val expectedException = Exception("Failed to fetch shows")

        coEvery { fetchShowsUseCase.invoke() } returns Result.failure(expectedException)

        viewModel.fetchShows()

        val expectedState = ListScreenState.Error(R.string.error)
        assertEquals(expectedState, viewModel.state.value)
    }

    @Test
    fun `shortList should update the listShows and state to Content with sorted shows`() {
        val initialShows = listOf(
            Show("2", "Show 2", "/aUrl2"),
            Show("3", "Show 3", "/aUrl3"),
            Show("1", "Show 1", "/aUrl1")
        )
        coEvery { fetchShowsUseCase.invoke() } returns Result.success(initialShows)

        viewModel.fetchShows()
        viewModel.shortList()

        val expectedShows = listOf(
            ShowUI("1", "Show 1", "/aUrl1"),
            ShowUI("2", "Show 2", "/aUrl2"),
            ShowUI("3", "Show 3", "/aUrl3")
        )
        assertEquals(ListScreenState.Content(expectedShows, true), viewModel.state.value)
    }
}
