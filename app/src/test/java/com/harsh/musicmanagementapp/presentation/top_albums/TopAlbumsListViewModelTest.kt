package com.harsh.musicmanagementapp.presentation.top_albums

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth
import com.harsh.musicmanagementapp.MainCoroutineRule
import com.harsh.musicmanagementapp.domain.use_case.get_top_albums.GetTopAlbumsUseCase
import com.harsh.musicmanagementapp.domain.use_case.search_artist.SearchArtistUseCase
import com.harsh.musicmanagementapp.getOrAwaitValueTest
import com.harsh.musicmanagementapp.presentation.search_artists.SearchArtistsViewModel
import com.harsh.musicmanagementapp.repositories.FakeMusicRepository
import com.harsh.musicmanagementapp.repositories.FakeRepoStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class TopAlbumsListViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Test
    fun `fetch artist's top albums, returns error`() {
        val viewModel =
            TopAlbumsListViewModel(
                GetTopAlbumsUseCase(FakeMusicRepository(FakeRepoStatus.THROW_ERROR)),
                SavedStateHandle()
            )

        viewModel.getTopAlbums("Diljit")
        val value = viewModel.errorMessage.getOrAwaitValueTest()
        Truth.assertThat(value.getContentIfNotHandled()).isEqualTo("Something went wrong!")
    }

    @Test
    fun `fetch artist's top albums, returns details`() {
        val viewModel =
            TopAlbumsListViewModel(
                GetTopAlbumsUseCase(FakeMusicRepository(FakeRepoStatus.SHOW_DATA)),
                SavedStateHandle()
            )

        viewModel.getTopAlbums("Diljit")
        val value = viewModel.topAlbums.getOrAwaitValueTest()
        Truth.assertThat(value.getContentIfNotHandled()?.size).isEqualTo(4)
    }

    @Test
    fun `fetch artist's top albums, returns empty`() {
        val viewModel =
            TopAlbumsListViewModel(
                GetTopAlbumsUseCase(FakeMusicRepository(FakeRepoStatus.SHOW_EMPTY)),
                SavedStateHandle()
            )

        viewModel.getTopAlbums("Diljit")
        val value = viewModel.topAlbums.getOrAwaitValueTest()
        Truth.assertThat(value.getContentIfNotHandled()?.size).isEqualTo(0)
    }
}