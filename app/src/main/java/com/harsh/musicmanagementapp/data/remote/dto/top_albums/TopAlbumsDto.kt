package com.harsh.musicmanagementapp.data.remote.dto.top_albums


import com.google.gson.annotations.SerializedName

data class TopAlbumsDto(
    @SerializedName("topalbums")
    val topAlbums: TopAlbums?
)