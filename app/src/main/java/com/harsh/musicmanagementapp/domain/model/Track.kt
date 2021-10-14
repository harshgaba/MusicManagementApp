package com.harsh.musicmanagementapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Track(
    @PrimaryKey(autoGenerate = true)
    val tableId:Int=0,
    val name: String?,
    val duration: Int?,
    val url: String?
)