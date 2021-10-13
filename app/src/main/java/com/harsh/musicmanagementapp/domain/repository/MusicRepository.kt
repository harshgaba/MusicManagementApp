package com.harsh.musicmanagementapp.domain.repository


interface MusicRepository {


    suspend fun searchArtist()


    suspend fun getAlbumInfo()


    suspend fun getTopAlbums()
}