package com.harsh.musicmanagementapp.data.remote.dto.top_albums


import com.google.gson.annotations.SerializedName
import com.harsh.musicmanagementapp.domain.model.Album

data class TopAlbum(
    @SerializedName("artist")
    val artist: Artist?,
    @SerializedName("image")
    val image: List<Image>?,
    @SerializedName("mbid")
    val mbid: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("playcount")
    val playCount: Int?,
    @SerializedName("url")
    val url: String?
)

fun TopAlbum.toAlbum(): Album {
    return Album(
        id = mbid,
        name = name ?:"",
        playCount = playCount,
        artist = artist?.name,
        tags = null,
        albumImageUrl = image?.last()?.text,
        tracks = null,
        summary = null,
        albumInfoLink = url,
        published = null
    )
}