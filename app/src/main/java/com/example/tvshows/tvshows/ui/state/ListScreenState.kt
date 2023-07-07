package com.example.tvshows.tvshows.ui.state

import androidx.annotation.StringRes
import com.example.tvshows.tvshows.ui.model.ShowUI

sealed class ListScreenState {
    object Loading : ListScreenState()
    data class Content(val shows: List<ShowUI>) : ListScreenState()
    data class Error(@StringRes val errorMessage: Int) : ListScreenState()
}
