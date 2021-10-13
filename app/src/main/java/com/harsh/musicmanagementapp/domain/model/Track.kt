package com.harsh.musicmanagementapp.domain.model

import androidx.room.Entity

@Entity
data class Track(
    val name: String?,
    val duration: Int?,
    val url: String?
)