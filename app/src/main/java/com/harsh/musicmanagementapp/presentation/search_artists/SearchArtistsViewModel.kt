package com.harsh.musicmanagementapp.presentation.search_artists

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.harsh.musicmanagementapp.domain.use_case.db_action_top_album.TopAlbumActionsUseCase
import com.harsh.musicmanagementapp.domain.use_case.get_album_info.GetAlbumInfoUseCase
import com.harsh.musicmanagementapp.domain.use_case.search_artist.SearchArtistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchArtistsViewModel @Inject constructor(
    private val searchArtistUseCase: SearchArtistUseCase
) : ViewModel() {


}