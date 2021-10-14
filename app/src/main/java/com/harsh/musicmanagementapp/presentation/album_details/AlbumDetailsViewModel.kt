package com.harsh.musicmanagementapp.presentation.album_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.harsh.musicmanagementapp.domain.use_case.db_action_top_album.TopAlbumActionsUseCase
import com.harsh.musicmanagementapp.domain.use_case.get_album_info.GetAlbumInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(
    private val getAlbumInfoUseCase: GetAlbumInfoUseCase,
    private val topAlbumActionsUseCase: TopAlbumActionsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {


}