package com.harsh.musicmanagementapp.data.remote.dto.search


import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("artistmatches")
    val artistMatches: ArtistMatches?,
    @SerializedName("@attr")
    val attr: Attr?,
    @SerializedName("opensearch:itemsPerPage")
    val openSearchItemsPerPage: String?,
    @SerializedName("opensearch:Query")
    val openSearchQuery: OpenSearchQuery?,
    @SerializedName("opensearch:startIndex")
    val openSearchStartIndex: String?,
    @SerializedName("opensearch:totalResults")
    val openSearchTotalResults: String?
)