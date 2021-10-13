package com.harsh.musicmanagementapp.domain.model


data class Album(
    val id: String?,
    val name: String?,
    val playCount: Int?,
    val artist: String?,
    val tags: List<String?>?,
    val albumImageUrl: String?,
    val trackNames: List<String?>?,
    val summary: String?,
    val albumInfoLink: String?,
    val published: String?
)
