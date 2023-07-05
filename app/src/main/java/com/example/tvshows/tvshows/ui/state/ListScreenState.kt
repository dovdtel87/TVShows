package com.example.tvshows.tvshows.ui.state

import androidx.annotation.StringRes
import com.example.tvshows.data.model.Show

sealed class ListScreenState {
    object Loading : ListScreenState()
    data class Content(val shows: List<Show>) : ListScreenState()
    data class Error(@StringRes val errorMessage: Int) : ListScreenState()
}
