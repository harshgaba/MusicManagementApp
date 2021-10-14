package com.harsh.musicmanagementapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.harsh.musicmanagementapp.domain.model.Album
import com.harsh.musicmanagementapp.domain.model.Track

@Database(
    entities = [Album::class, Track::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class TopAlbumDatabase : RoomDatabase() {

    abstract fun topAlbumDao(): TopAlbumDao

}