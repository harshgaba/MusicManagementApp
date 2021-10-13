package com.harsh.musicmanagementapp.data.remote.dto.album_info


import com.google.gson.annotations.SerializedName
import com.harsh.musicmanagementapp.domain.model.Album

data class AlbumInfoDto(
    @SerializedName("album")
    val albumInfo: AlbumInfo?
)

fun AlbumInfoDto.toAlbum(): Album {
    return Album(
        id = albumInfo?.mbid,
        name = albumInfo?.name,
        playCount = albumInfo?.playcount?.toIntOrNull(),
        artist = albumInfo?.artist,
        tags = albumInfo?.tags?.tag?.map { tag -> tag.name }?.toList(),
        albumImageUrl = albumInfo?.image?.last()?.text,
        trackNames = albumInfo?.tracks?.track?.map { track -> track.name }?.toList(),
        summary = albumInfo?.wiki?.summary,
        albumInfoLink = albumInfo?.url,
        published = albumInfo?.wiki?.published
    )
}