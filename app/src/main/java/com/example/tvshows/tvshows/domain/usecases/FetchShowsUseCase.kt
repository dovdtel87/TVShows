package com.example.tvshows.tvshows.domain.usecases

import com.example.tvshows.data.model.Show
import com.example.tvshows.data.repository.ShowsRepository
import javax.inject.Inject

class FetchShowsUseCase @Inject constructor(
    private val showsRepository: ShowsRepository
) {
    suspend fun invoke() : Result<List<Show>> {
        return try {
            showsRepository.fetchShows()
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
