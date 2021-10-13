package com.harsh.musicmanagementapp.data.remote.dto.search


import com.google.gson.annotations.SerializedName

data class ArtistMatches(
    @SerializedName("artist")
    val searchedArtist: List<SearchedArtist>?
)