package com.harsh.musicmanagementapp.data.remote.dto.album_info


import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)