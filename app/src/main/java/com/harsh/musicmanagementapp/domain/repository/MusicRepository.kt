package com.harsh.musicmanagementapp.domain.repository

import com.harsh.musicmanagementapp.data.remote.dto.album_info.AlbumInfoDto
import com.harsh.musicmanagementapp.data.remote.dto.search.ArtistSearchDto
import com.harsh.musicmanagementapp.data.remote.dto.top_albums.TopAlbumsDto
import retrofit2.http.QueryMap


interface MusicRepository {


    suspend fun searchArtist(artist: String?): ArtistSearchDto


    suspend fun getAlbumInfo(artist: String?, album: String?): AlbumInfoDto


    suspend fun getTopAlbums(artist: String?): TopAlbumsDto
}