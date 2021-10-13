package com.harsh.musicmanagementapp.data.remote.dto.top_albums


import com.google.gson.annotations.SerializedName

data class TopAlbums(
    @SerializedName("album")
    val topAlbums: List<TopAlbum>?,
    @SerializedName("@attr")
    val attr: Attr?
)