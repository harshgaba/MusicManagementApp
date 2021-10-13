package com.harsh.musicmanagementapp.data.remote.dto.album_info


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("size")
    val size: String?,
    @SerializedName("#text")
    val text: String?
)