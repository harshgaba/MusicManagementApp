package com.harsh.musicmanagementapp.data.remote.dto.album_info


import com.google.gson.annotations.SerializedName

data class Streamable(
    @SerializedName("fulltrack")
    val fulltrack: String?,
    @SerializedName("#text")
    val text: String?
)