package com.harsh.musicmanagementapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.harsh.musicmanagementapp.domain.model.Album

@Dao
interface TopAlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopAlbum(album: Album)

    @Delete
    suspend fun deleteTopAlbum(album: Album)

    @Query("SELECT * FROM top_album")
    fun observeAllTopAlbums(): LiveData<List<Album>>
}