package com.harsh.musicmanagementapp.data.remote.dto.search


import com.google.gson.annotations.SerializedName

data class OpenSearchQuery(
    @SerializedName("role")
    val role: String?,
    @SerializedName("searchTerms")
    val searchTerms: String?,
    @SerializedName("startPage")
    val startPage: String?,
    @SerializedName("#text")
    val text: String?
)