package com.harsh.musicmanagementapp.presentation.search_artists

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.harsh.musicmanagementapp.MainCoroutineRule
import com.harsh.musicmanagementapp.domain.use_case.db_action_top_album.TopAlbumActionsUseCase
import com.harsh.musicmanagementapp.domain.use_case.search_artist.SearchArtistUseCase
import com.harsh.musicmanagementapp.getOrAwaitValueTest
import com.harsh.musicmanagementapp.presentation.saved_top_albums.SavedTopAlbumsViewModel
import com.harsh.musicmanagementapp.repositories.FakeMusicRepository
import com.harsh.musicmanagementapp.repositories.FakeRepoStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchArtistsViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    @Test
    fun `fetch artists, returns error`() {
        val viewModel =
            SearchArtistsViewModel(
                SearchArtistUseCase(FakeMusicRepository(FakeRepoStatus.THROW_ERROR)),
            )

        viewModel.searchArtists("Diljit")
        val value = viewModel.errorMessage.getOrAwaitValueTest()
        Truth.assertThat(value.getContentIfNotHandled()).isEqualTo("Something went wrong!")
    }

    @Test
    fun `fetch artists, returns details`() {
        val viewModel =
            SearchArtistsViewModel(
                SearchArtistUseCase(FakeMusicRepository(FakeRepoStatus.SHOW_DATA)),
            )

        viewModel.searchArtists("Diljit")
        val value = viewModel.artists.getOrAwaitValueTest()
        Truth.assertThat(value.getContentIfNotHandled()?.size).isEqualTo(1)
    }

    @Test
    fun `fetch artists, returns empty`() {
        val viewModel =
            SearchArtistsViewModel(
                SearchArtistUseCase(FakeMusicRepository(FakeRepoStatus.SHOW_EMPTY)),
            )

        viewModel.searchArtists("Diljit")
        val value = viewModel.artists.getOrAwaitValueTest()
        Truth.assertThat(value.getContentIfNotHandled()?.size).isEqualTo(0)
    }
}