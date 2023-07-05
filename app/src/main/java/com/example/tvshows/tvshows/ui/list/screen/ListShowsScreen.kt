package com.example.tvshows.tvshows.ui.list.screen

import androidx.compose.runtime.Composable
import com.example.tvshows.tvshows.ui.ListShowsViewModel

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tvshows.tvshows.ui.list.components.ErrorView
import com.example.tvshows.tvshows.ui.list.components.ListShows
import com.example.tvshows.tvshows.ui.list.components.LoadingView
import com.example.tvshows.tvshows.ui.state.ListScreenState

@Composable
fun ListShowsScreen(
    viewModel: ListShowsViewModel = hiltViewModel(),
) {
    when (val uiState = viewModel.state.collectAsStateWithLifecycle().value) {
        is ListScreenState.Loading -> {
            LoadingView()
        }

        is ListScreenState.Error -> {
            ErrorView(uiState.errorMessage) {
                viewModel.fetchShows()
            }
        }

        is ListScreenState.Content -> {
            ListShows(
                shows = uiState.shows,
                onShortList = {}
            )
        }
    }
}
