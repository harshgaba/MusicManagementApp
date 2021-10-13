package com.harsh.musicmanagementapp.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.harsh.musicmanagementapp.domain.model.Album
import com.harsh.musicmanagementapp.domain.model.Track
import com.harsh.musicmanagementapp.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class TopAlbumDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: TopAlbumDatabase
    private lateinit var dao: TopAlbumDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TopAlbumDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.topAlbumDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertTopAlbum() = runBlockingTest {
        val topAlbum = Album(id = "1234",
            name = "Moon Light",
            playCount = 34567,
            artist = "Diljit",
            tags = listOf("Pop","Punjabi","Trance"),
            albumImageUrl = "https://imageDiljit.png",
            tracks = listOf<Track>(
                Track(
                    name = "Black White",
                    duration = 2,
                    url = "https://imageDiljit.mp3")
            ),
            summary = "New album",
            albumInfoLink = "https://imageDiljit/moonlight",
            published = "03 Mar 2010, 16:48"
        )
        dao.insertTopAlbum(topAlbum)

        val allTopAlbum = dao.observeAllTopAlbums().getOrAwaitValue()

        assertThat(allTopAlbum).contains(topAlbum)
    }

    @Test
    fun deleteShoppingItem() = runBlockingTest {
        val topAlbum = Album(id = "1234",
            name = "Moon Light",
            playCount = 34567,
            artist = "Diljit",
            tags = listOf("Pop","Punjabi","Trance"),
            albumImageUrl = "https://imageDiljit.png",
            tracks = listOf<Track>(
                Track(
                    name = "Black White",
                    duration = 2,
                    url = "https://imageDiljit.mp3")
            ),
            summary = "New album",
            albumInfoLink = "https://imageDiljit/moonlight",
            published = "03 Mar 2010, 16:48"
        )
        dao.insertTopAlbum(topAlbum)
        dao.deleteTopAlbum(topAlbum)

        val allTopAlbum = dao.observeAllTopAlbums().getOrAwaitValue()

        assertThat(allTopAlbum).doesNotContain(topAlbum)
    }

}