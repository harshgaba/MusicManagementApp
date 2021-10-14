package com.harsh.musicmanagementapp.domain.use_case.get_album_info

import com.harsh.musicmanagementapp.data.remote.dto.album_info.toAlbum
import com.harsh.musicmanagementapp.domain.model.Album
import com.harsh.musicmanagementapp.domain.repository.MusicRepository
import com.harsh.musicmanagementapp.shared.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAlbumInfoUseCase @Inject constructor(private val repository: MusicRepository) {

    operator fun invoke(artist: String?, albumName: String?): Flow<Resource<Album>> = flow {

        try {
            emit(Resource.Loading<Album>())
            val album = repository.getAlbumInfo(artist, albumName).toAlbum()
            emit(Resource.Success<Album>(album))
        } catch (e: Exception) {
            emit(
                Resource.Error<Album>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        }
    }
}