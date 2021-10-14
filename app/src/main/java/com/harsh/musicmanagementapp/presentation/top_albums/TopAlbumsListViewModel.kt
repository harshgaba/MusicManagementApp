package com.harsh.musicmanagementapp.presentation.top_albums

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.harsh.musicmanagementapp.domain.use_case.get_top_albums.GetTopAlbumsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopAlbumsListViewModel @Inject constructor(
    private val getTopAlbumsUseCase: GetTopAlbumsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {


}