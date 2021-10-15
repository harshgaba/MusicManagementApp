package com.harsh.musicmanagementapp.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.harsh.musicmanagementapp.data.remote.dto.album_info.AlbumInfo
import com.harsh.musicmanagementapp.data.remote.dto.album_info.AlbumInfoDto
import com.harsh.musicmanagementapp.data.remote.dto.search.*
import com.harsh.musicmanagementapp.data.remote.dto.top_albums.TopAlbum
import com.harsh.musicmanagementapp.data.remote.dto.top_albums.TopAlbums
import com.harsh.musicmanagementapp.data.remote.dto.top_albums.TopAlbumsDto
import com.harsh.musicmanagementapp.domain.model.Album
import com.harsh.musicmanagementapp.domain.model.Track
import com.harsh.musicmanagementapp.domain.repository.MusicRepository

class FakeMusicRepository constructor(private val status: FakeRepoStatus) : MusicRepository {
    private val topAlbums = mutableListOf<Album>()

    private val observableTopAlbums = MutableLiveData<List<Album>>(topAlbums)

    private fun refreshLiveData() {
        observableTopAlbums.postValue(topAlbums)
    }


    override suspend fun insertTopAlbum(album: Album) {
        topAlbums.add(album)
        refreshLiveData()
    }

    override suspend fun deleteTopAlbum(album: Album) {
        topAlbums.remove(album)
        refreshLiveData()
    }

    override suspend fun getAlbumByName(name: String): Album? {
        return topAlbums.find { it.name.equals(name,true) }
    }

    override fun observeAllTopAlbums(): LiveData<List<Album>> {

        return when (status) {
            FakeRepoStatus.THROW_ERROR -> {
                throw Exception("Something went wrong!")
            }
            FakeRepoStatus.SHOW_DATA -> {
                topAlbums.add(createMockAlbum())
                refreshLiveData()
                observableTopAlbums
            }
            FakeRepoStatus.SHOW_EMPTY -> {
                topAlbums.clear()
                refreshLiveData()
                observableTopAlbums
            }
        }
    }

    override suspend fun searchArtist(artist: String?): ArtistSearchDto {
        return when (status) {
            FakeRepoStatus.THROW_ERROR -> {
                throw Exception("Something went wrong!")
            }
            FakeRepoStatus.SHOW_DATA -> {

                ArtistSearchDto(
                    Results(
                        ArtistMatches(createMockSearchArtists(artist)),
                        null,
                        null,
                        null,
                        null,
                        null
                    )
                )
            }
            FakeRepoStatus.SHOW_EMPTY -> {
                ArtistSearchDto(
                    Results(ArtistMatches(emptyList()), null, null, null, null, null)
                )
            }
        }
    }

    override suspend fun getAlbumInfo(artist: String?, album: String?): AlbumInfoDto {
        return when (status) {
            FakeRepoStatus.THROW_ERROR -> {
                throw Exception("Something went wrong!")
            }
            FakeRepoStatus.SHOW_DATA -> {

                AlbumInfoDto(createMockAlbumInfo(artist, album))
            }
            FakeRepoStatus.SHOW_EMPTY -> {
                AlbumInfoDto(null)
            }
        }
    }

    override suspend fun getTopAlbums(artist: String?): TopAlbumsDto {
        return when (status) {
            FakeRepoStatus.THROW_ERROR -> {
                throw Exception("Something went wrong!")
            }
            FakeRepoStatus.SHOW_DATA -> {
                TopAlbumsDto(TopAlbums(createMockTopAlbumList(), null))
            }
            FakeRepoStatus.SHOW_EMPTY -> {
                TopAlbumsDto(TopAlbums(emptyList(), null))
            }
        }
    }


    private fun createMockTopAlbumList(): List<TopAlbum> {
        val albumList = mutableListOf<TopAlbum>()
        albumList.add(createMockTopAlbum("1a"))
        albumList.add(createMockTopAlbum("2a"))
        albumList.add(createMockTopAlbum("3a"))
        albumList.add(createMockTopAlbum("4a"))
        return albumList
    }

    private fun createMockAlbumInfo(artist: String?, album: String?): AlbumInfo {
        return AlbumInfo(
            mbid = "123456",
            name = album,
            playcount = "34567",
            artist = artist,
            tags = null,
            url = "https://imageDiljit.png",
            tracks = null,
            wiki = null,
            image = null,
            listeners = "3456"
        )
    }

    private fun createMockTopAlbum(albumId: String): TopAlbum {
        return TopAlbum(
            mbid = albumId,
            name = "Moon Light",
            playCount = 34567,
            artist = null,
            url = "https://imageDiljit.png",
            image = null,
        )
    }

    private fun createMockSearchArtists(artist: String?): List<SearchedArtist> {
        val artistsList = mutableListOf<SearchedArtist>()
        artistsList.add(
            SearchedArtist(listOf<Image>(), "23456", "12", artist, "", "https://imageDiljit.png")
        )
        return artistsList
    }

    private fun createMockAlbum(): Album {
        return Album(

            id = "1234",
            name = "Moon Light",
            playCount = 34567,
            artist = "Diljit",
            tags = listOf<String>("Pop", "Punjabi", "Trance"),
            albumImageUrl = "https://imageDiljit.png",
            tracks = listOf<Track>(
                Track(
                    name = "Black White",
                    duration = "2 min",
                    rank = 1,
                    artist = "Diljit"
                )
            ),
            summary = "New album",
            albumInfoLink = "https://imageDiljit/moonlight",
            published = "03 Mar 2010, 16:48"
        )
    }
}