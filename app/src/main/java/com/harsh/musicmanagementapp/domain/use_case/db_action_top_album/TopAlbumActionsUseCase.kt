package com.harsh.musicmanagementapp.domain.use_case.db_action_top_album

import com.harsh.musicmanagementapp.domain.model.Album
import com.harsh.musicmanagementapp.domain.repository.MusicRepository
import com.harsh.musicmanagementapp.shared.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TopAlbumActionsUseCase @Inject constructor(private val repository: MusicRepository) {


    fun insertTopAlbum(album: Album): Flow<Resource<Unit>> = flow {
        try {
            repository.insertTopAlbum(album)
            emit(Resource.Success<Unit>(Unit))
        } catch (e: Exception) {
            emit(
                Resource.Error<Unit>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        }
    }

    fun deleteTopAlbum(album: Album): Flow<Resource<Unit>> = flow {
        try {
            repository.deleteTopAlbum(album)
            emit(Resource.Success<Unit>(Unit))
        } catch (e: Exception) {
            emit(
                Resource.Error<Unit>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        }
    }

    fun getAlbumById(id: String?): Flow<Resource<Album?>> = flow {
        try {
            val album = repository.getAlbumById(id)
            emit(Resource.Success<Album?>(album))
        } catch (e: Exception) {
            emit(
                Resource.Error<Album?>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        }
    }

    fun getTopAlbums(): Flow<Resource<List<Album>?>> = flow {

        try {
            emit(Resource.Loading<List<Album>?>())
            val topAlbums = repository.observeAllTopAlbums().value
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