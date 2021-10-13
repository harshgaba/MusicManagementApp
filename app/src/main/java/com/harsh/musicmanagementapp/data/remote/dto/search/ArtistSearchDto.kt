package com.harsh.musicmanagementapp.data.remote.dto.search


import com.google.gson.annotations.SerializedName

data class ArtistSearchDto(
    @SerializedName("results")
    val results: Results?
)