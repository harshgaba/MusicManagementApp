package com.harsh.musicmanagementapp.presentation.saved_top_albums

import androidx.lifecycle.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.harsh.musicmanagementapp.domain.model.Album
import com.harsh.musicmanagementapp.domain.use_case.db_action_top_album.TopAlbumActionsUseCase
import com.harsh.musicmanagementapp.shared.Event
import com.harsh.musicmanagementapp.shared.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SavedTopAlbumsViewModel @Inject constructor(
    private val topAlbumActionsUseCase: TopAlbumActionsUseCase) : ViewModel() {

    var topAlbums: LiveData<List<Album>>? = null

    private val _errorMessage = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>> = _errorMessage

    private val _isLoading = MutableLiveData<Event<Boolean>>()
    val isLoading: LiveData<Event<Boolean>> = _isLoading

    init {
        getAllSavedTopAlbums()
    }

     fun getAllSavedTopAlbums() {
        topAlbumActionsUseCase.getTopAlbums().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    topAlbums= result.data
                    _isLoading.postValue(Event(false))
                }
                is Resource.Error -> {
                    _isLoading.postValue(Event(false))
                    _errorMessage.postValue(Event(result.message ?: "An unexpected error occurred"))
                }
                is Resource.Loading -> {
                    _isLoading.postValue(Event(true))
                }
            }
        }.launchIn(viewModelScope)
    }
}