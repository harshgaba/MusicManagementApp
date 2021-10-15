package com.harsh.musicmanagementapp.presentation.album_details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.harsh.musicmanagementapp.MainCoroutineRule
import com.harsh.musicmanagementapp.domain.use_case.db_action_top_album.TopAlbumActionsUseCase
import com.harsh.musicmanagementapp.domain.use_case.get_album_info.GetAlbumInfoUseCase
import com.harsh.musicmanagementapp.getOrAwaitValueTest
import com.harsh.musicmanagementapp.repositories.FakeMusicRepository
import com.harsh.musicmanagementapp.repositories.FakeRepoStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import com.harsh.musicmanagementapp.domain.model.Album
import com.harsh.musicmanagementapp.domain.model.Track


@ExperimentalCoroutinesApi
class AlbumDetailsViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    @Test
    fun `fetch Album Details, returns error`() {
        val viewModel =
            AlbumDetailsViewModel(
                GetAlbumInfoUseCase(FakeMusicRepository(FakeRepoStatus.THROW_ERROR)),
                TopAlbumActionsUseCase(FakeMusicRepository(FakeRepoStatus.THROW_ERROR)),
                SavedStateHandle()
            )

        viewModel.getAlbumInfo("Diljit", "Moon Light")
        val value = viewModel.errorMessage.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()).isEqualTo("Something went wrong!")
    }

    @Test
    fun `fetch Album Details, returns details`() {
        val viewModel =
            AlbumDetailsViewModel(
                GetAlbumInfoUseCase(FakeMusicRepository(FakeRepoStatus.SHOW_DATA)),
                TopAlbumActionsUseCase(FakeMusicRepository(FakeRepoStatus.SHOW_DATA)),
                SavedStateHandle()
            )

        viewModel.getAlbumInfo("Diljit", "Moon Light")
        val value = viewModel.album.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.name).isEqualTo("Moon Light")
    }

    @Test
    fun `fetch Album Details, returns empty`() {
        val viewModel =
            AlbumDetailsViewModel(
                GetAlbumInfoUseCase(FakeMusicRepository(FakeRepoStatus.SHOW_EMPTY)),
                TopAlbumActionsUseCase(FakeMusicRepository(FakeRepoStatus.SHOW_EMPTY)),
                SavedStateHandle()
            )

        viewModel.getAlbumInfo("Diljit", "Moon Light")
        val value = viewModel.album.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.id).isEqualTo(null)
    }


    @Test
    fun `insert top album, returns success`() {
        val viewModel =
            AlbumDetailsViewModel(
                GetAlbumInfoUseCase(FakeMusicRepository(FakeRepoStatus.SHOW_EMPTY)),
                TopAlbumActionsUseCase(FakeMusicRepository(FakeRepoStatus.SHOW_EMPTY)),
                SavedStateHandle()
            )
        viewModel.insertTopAlbum(
            Album(

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
        )

        val value = viewModel.albumSaved.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()).isEqualTo(true)
    }

    @Test
    fun `delete top album, returns success`() {
      val album=Album(

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
          val viewModel =
            AlbumDetailsViewModel(
                GetAlbumInfoUseCase(FakeMusicRepository(FakeRepoStatus.SHOW_EMPTY)),
                TopAlbumActionsUseCase(FakeMusicRepository(FakeRepoStatus.SHOW_EMPTY)),
                SavedStateHandle()
            )
        viewModel.insertTopAlbum(album)
        viewModel.deleteTopAlbum(album)

        val value = viewModel.albumSaved.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()).isEqualTo(false)
    }


    @Test
    fun `get Album Details from database, returns error`() {
        val album = Album(

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
        val viewModel =
            AlbumDetailsViewModel(
                GetAlbumInfoUseCase(FakeMusicRepository(FakeRepoStatus.THROW_ERROR)),
                TopAlbumActionsUseCase(FakeMusicRepository(FakeRepoStatus.THROW_ERROR)),
                SavedStateHandle()
            )
        viewModel.insertTopAlbum(album)
        viewModel.getAlbumInfoFromDB("Moon Light")
        val value = viewModel.errorMessage.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()).isEqualTo("Something went wrong!")
    }

    @Test
    fun `get Album Details from database, returns details`() {
        val album = Album(

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
        val viewModel =
            AlbumDetailsViewModel(
                GetAlbumInfoUseCase(FakeMusicRepository(FakeRepoStatus.THROW_ERROR)),
                TopAlbumActionsUseCase(FakeMusicRepository(FakeRepoStatus.THROW_ERROR)),
                SavedStateHandle()
            )
        viewModel.insertTopAlbum(album)
        viewModel.getAlbumInfoFromDB("Moon Light")
        val value = viewModel.album.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.id).isEqualTo("1234")
    }

    @Test
    fun `get Album Details from database, returns empty`() {
        val viewModel =
            AlbumDetailsViewModel(
                GetAlbumInfoUseCase(FakeMusicRepository(FakeRepoStatus.THROW_ERROR)),
                TopAlbumActionsUseCase(FakeMusicRepository(FakeRepoStatus.THROW_ERROR)),
                SavedStateHandle()
            )
        viewModel.getAlbumInfoFromDB("Moon Light")
        val value = viewModel.album.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()).isEqualTo(null)
    }

}