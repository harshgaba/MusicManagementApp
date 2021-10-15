package com.harsh.musicmanagementapp.domain.use_case.search_artist

import com.harsh.musicmanagementapp.data.remote.dto.search.toArtist
import com.harsh.musicmanagementapp.domain.model.Artist
import com.harsh.musicmanagementapp.domain.repository.MusicRepository
import com.harsh.musicmanagementapp.shared.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchArtistUseCase @Inject constructor(private val repository: MusicRepository) {

    operator fun invoke(artist: String?): Flow<Resource<List<Artist>?>> = flow {

        try {
            emit(Resource.Loading<List<Artist>?>())
            val artists =
                repository.searchArtist(artist).results?.artistMatches?.searchedArtist?.map { it.toArtist() }
            emit(Resource.Success<List<Artist>?>(artists))
        } catch (e: Exception) {
            emit(
                Resource.Error<List<Artist>?>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        }
    }
}