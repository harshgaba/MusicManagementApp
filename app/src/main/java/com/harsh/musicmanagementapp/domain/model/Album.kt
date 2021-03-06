package com.harsh.musicmanagementapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "top_album")
data class Album(
    val id: String?,
    @PrimaryKey
    val name: String,
    val playCount: Int?,
    val artist: String?,
    val tags: List<String?>?,
    val albumImageUrl: String?,
    val tracks: List<Track>?,
    val summary: String?,
    val albumInfoLink: String?,
    val published: String?
)
