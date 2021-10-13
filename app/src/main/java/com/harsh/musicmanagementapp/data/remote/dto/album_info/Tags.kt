package com.harsh.musicmanagementapp.data.remote.dto.album_info


import com.google.gson.annotations.SerializedName

data class Tags(
    @SerializedName("tag")
    val tag: List<Tag>?
)