package com.harsh.musicmanagementapp.data.remote.dto.top_albums


import com.google.gson.annotations.SerializedName

data class Attr(
    @SerializedName("artist")
    val artist: String?,
    @SerializedName("page")
    val page: String?,
    @SerializedName("perPage")
    val perPage: String?,
    @SerializedName("total")
    val total: String?,
    @SerializedName("totalPages")
    val totalPages: String?
)