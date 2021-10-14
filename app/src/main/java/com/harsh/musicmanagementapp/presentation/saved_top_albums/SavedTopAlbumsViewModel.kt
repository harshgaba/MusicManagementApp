package com.harsh.musicmanagementapp.presentation.saved_top_albums

import androidx.lifecycle.ViewModel
import com.harsh.musicmanagementapp.domain.use_case.db_action_top_album.TopAlbumActionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SavedTopAlbumsViewModel @Inject constructor(
    private val topAlbumActionsUseCase: TopAlbumActionsUseCase
) : ViewModel() {


}