package com.harsh.musicmanagementapp.data.remote.dto.album_info


import com.google.gson.annotations.SerializedName
import com.harsh.musicmanagementapp.domain.model.Album
import com.harsh.musicmanagementapp.domain.model.Track

data class AlbumInfoDto(
    @SerializedName("album")
    val albumInfo: AlbumInfo?
)

fun AlbumInfoDto.toAlbum(): Album {
    return Album(
        id = albumInfo?.mbid ,
        name = albumInfo?.name,
        playCount = albumInfo?.playcount?.toIntOrNull(),
        artist = albumInfo?.artist,
        tags = albumInfo?.tags?.tag?.map { tag -> tag.name }?.toList(),
        albumImageUrl = albumInfo?.image?.last()?.text,
        tracks = albumInfo?.tracks?.track?.map { track ->
            Track(
                name = track.name,
                duration = track.duration?.let {
                    if (track.duration <= 60) "{${track.duration} Sec}" else "{${(track.duration / 60)} Min}"
                },
                rank = track.attr?.rank,
                artist = track.artist?.name
            )
        }?.toList(),
        summary = albumInfo?.wiki?.summary,
        albumInfoLink = albumInfo?.url,
        published = albumInfo?.wiki?.published
    )
}