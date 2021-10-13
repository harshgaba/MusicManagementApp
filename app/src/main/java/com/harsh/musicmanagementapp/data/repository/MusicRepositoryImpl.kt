package com.harsh.musicmanagementapp.data.repository

import com.harsh.musicmanagementapp.data.remote.MusicApi
import com.harsh.musicmanagementapp.domain.repository.MusicRepository
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(private val api: MusicApi) : MusicRepository {

    override suspend fun searchArtist() {
        TODO("Not yet implemented")
    }

    override suspend fun getAlbumInfo() {
        TODO("Not yet implemented")
    }

    override suspend fun getTopAlbums() {
        TODO("Not yet implemented")
    }

}