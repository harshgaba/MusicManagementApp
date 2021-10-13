package com.harsh.musicmanagementapp.data.repository

import com.harsh.musicmanagementapp.BuildConfig.LastFMAPIKey
import com.harsh.musicmanagementapp.data.remote.MusicApi
import com.harsh.musicmanagementapp.data.remote.dto.album_info.AlbumInfoDto
import com.harsh.musicmanagementapp.data.remote.dto.search.ArtistSearchDto
import com.harsh.musicmanagementapp.data.remote.dto.top_albums.TopAlbumsDto
import com.harsh.musicmanagementapp.domain.repository.MusicRepository
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(private val api: MusicApi) : MusicRepository {

    override suspend fun searchArtist(artist: String?): ArtistSearchDto {
        return api.searchArtist(getDefaultParametersMap().apply {
            put("method", "artist.search")
            put("artist", artist)
        })
    }

    override suspend fun getAlbumInfo(artist: String?, album: String?): AlbumInfoDto {
        return api.getAlbumInfo(getDefaultParametersMap().apply {
            put("method", "album.getinfo")
            put("artist", artist)
            put("album", album)
        })
    }

    override suspend fun getTopAlbums(artist: String?): TopAlbumsDto {
        return api.getTopAlbums(getDefaultParametersMap().apply {
            put("method", "artist.gettopalbums")
            put("artist", artist)
        })
    }


    private fun getDefaultParametersMap(): HashMap<String, String?> =
        HashMap<String, String?>().apply {
            put("api_key", LastFMAPIKey)
            put("format", "json")
        }

}