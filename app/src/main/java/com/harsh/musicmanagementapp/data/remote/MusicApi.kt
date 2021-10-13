package com.harsh.musicmanagementapp.data.remote

import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MusicApi {

    @GET("/")
    suspend fun searchArtist(@QueryMap searchQueryMap: Map<String, String>)

    @GET("/")
    suspend fun getAlbumInfo(@QueryMap requestQueryMap: Map<String, String>)

    @GET("/")
    suspend fun getTopAlbums(@QueryMap requestQueryMap: Map<String, String>)

}