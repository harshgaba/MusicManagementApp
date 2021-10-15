package com.harsh.musicmanagementapp.data.remote.dto.search


import com.google.gson.annotations.SerializedName
import com.harsh.musicmanagementapp.domain.model.Artist

data class SearchedArtist(
    @SerializedName("image")
    val image: List<Image>?,
    @SerializedName("listeners")
    val listeners: String?,
    @SerializedName("mbid")
    val mbid: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("streamable")
    val streamable: String?,
    @SerializedName("url")
    val url: String?
)

fun SearchedArtist.toArtist(): Artist {
    return Artist(
        id = mbid,
        name = name,
        imageUrl = if (!image.isNullOrEmpty()) image.last().text else null,
        listeners = listeners,
        moreInfoLink = url
    )
}