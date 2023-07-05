package com.example.tvshows.tvshows.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvshows.R
import com.example.tvshows.data.model.Show
import com.example.tvshows.tvshows.domain.usecases.FetchShowsUseCase
import com.example.tvshows.tvshows.ui.state.ListScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListShowsViewModel @Inject constructor(
    private val fetchShowsUseCase: FetchShowsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<ListScreenState>(ListScreenState.Loading)
    val state = _state.asStateFlow()

    private var listShows : List<Show> = emptyList()

    init {
        fetchShows()
    }

    fun fetchShows() {
        viewModelScope.launch {
            showLoading()
            fetchShowsUseCase.invoke()
                .onSuccess { shows ->
                    listShows = shows
                    updateListState()
                }.onFailure {
                    _state.update {
                        ListScreenState.Error(R.string.error)
                    }
                }
        }
    }

    private fun showLoading() {
        _state.update {
            ListScreenState.Loading
        }
    }

    private fun updateListState() {
        _state.update {
            ListScreenState.Content(listShows)
        }
    }
}
