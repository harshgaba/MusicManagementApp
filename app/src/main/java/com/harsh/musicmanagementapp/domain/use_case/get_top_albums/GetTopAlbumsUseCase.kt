package com.harsh.musicmanagementapp.domain.use_case.get_top_albums

import com.harsh.musicmanagementapp.data.remote.dto.top_albums.toAlbum
import com.harsh.musicmanagementapp.domain.model.Album
import com.harsh.musicmanagementapp.domain.repository.MusicRepository
import com.harsh.musicmanagementapp.shared.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTopAlbumsUseCase @Inject constructor(private val repository: MusicRepository) {

    operator fun invoke(artist: String?): Flow<Resource<List<Album>?>> = flow {

        try {
            emit(Resource.Loading<List<Album>?>())
            val topAlbums =
                repository.getTopAlbums(artist).topAlbums?.topAlbums?.map { it.toAlbum() }

            emit(Resource.Success<List<Album>?>(topAlbums))
        } catch (e: Exception) {
            emit(
                Resource.Error<List<Album>?>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        }
    }
}