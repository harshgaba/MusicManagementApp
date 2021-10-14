package com.harsh.musicmanagementapp.domain.repository

import androidx.lifecycle.LiveData
import com.harsh.musicmanagementapp.data.remote.dto.album_info.AlbumInfoDto
import com.harsh.musicmanagementapp.data.remote.dto.search.ArtistSearchDto
import com.harsh.musicmanagementapp.data.remote.dto.top_albums.TopAlbumsDto
import com.harsh.musicmanagementapp.domain.model.Album


interface MusicRepository {


    suspend fun insertTopAlbum(album: Album)

    suspend fun deleteTopAlbum(album: Album)

    fun observeAllTopAlbums(): LiveData<List<Album>>

    suspend fun searchArtist(artist: String?): ArtistSearchDto


    suspend fun getAlbumInfo(artist: String?, album: String?): AlbumInfoDto


    suspend fun getTopAlbums(artist: String?): TopAlbumsDto
}