package com.harsh.musicmanagementapp.data.remote

import com.harsh.musicmanagementapp.data.remote.dto.album_info.AlbumInfoDto
import com.harsh.musicmanagementapp.data.remote.dto.search.ArtistSearchDto
import com.harsh.musicmanagementapp.data.remote.dto.top_albums.TopAlbumsDto
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MusicApi {

    @GET("/")
    suspend fun searchArtist(@QueryMap searchQueryMap: Map<String, String?>) : ArtistSearchDto

    @GET("/")
    suspend fun getAlbumInfo(@QueryMap requestQueryMap: Map<String, String?>) : AlbumInfoDto

    @GET("/")
    suspend fun getTopAlbums(@QueryMap requestQueryMap: Map<String, String?>) :TopAlbumsDto

}