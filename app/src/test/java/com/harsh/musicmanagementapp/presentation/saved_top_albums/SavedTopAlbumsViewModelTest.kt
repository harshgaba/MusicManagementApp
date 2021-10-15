package com.harsh.musicmanagementapp.presentation.saved_top_albums


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.harsh.musicmanagementapp.MainCoroutineRule
import com.harsh.musicmanagementapp.domain.use_case.db_action_top_album.TopAlbumActionsUseCase
import com.harsh.musicmanagementapp.getOrAwaitValueTest
import com.harsh.musicmanagementapp.repositories.FakeMusicRepository
import com.harsh.musicmanagementapp.repositories.FakeRepoStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SavedTopAlbumsViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Test
    fun `fetch top albums, returns error`() {
        val viewModel =
            SavedTopAlbumsViewModel(
                TopAlbumActionsUseCase(FakeMusicRepository(FakeRepoStatus.THROW_ERROR)),
            )

        viewModel.getAllSavedTopAlbums()
        val value = viewModel.errorMessage.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()).isEqualTo("Something went wrong!")
    }

    @Test
    fun `fetch top albums, returns details`() {
        val viewModel =
            SavedTopAlbumsViewModel(
                TopAlbumActionsUseCase(FakeMusicRepository(FakeRepoStatus.SHOW_DATA))
            )

        viewModel.getAllSavedTopAlbums()
        val value = viewModel.topAlbums?.getOrAwaitValueTest()
        assertThat(value?.size).isEqualTo(2)
    }

    @Test
    fun `fetch top albums, returns empty`() {
        val viewModel =
            SavedTopAlbumsViewModel(
                TopAlbumActionsUseCase(FakeMusicRepository(FakeRepoStatus.SHOW_EMPTY)),
            )

        viewModel.getAllSavedTopAlbums()
        val value = viewModel.topAlbums?.getOrAwaitValueTest()
        assertThat(value?.size).isEqualTo(0)
    }

}