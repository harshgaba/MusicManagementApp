package com.harsh.musicmanagementapp.presentation.top_albums

import androidx.lifecycle.*
import com.harsh.musicmanagementapp.domain.use_case.get_top_albums.GetTopAlbumsUseCase
import com.harsh.musicmanagementapp.shared.Constants
import com.harsh.musicmanagementapp.shared.Event
import com.harsh.musicmanagementapp.shared.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TopAlbumsListViewModel @Inject constructor(
    private val getTopAlbumsUseCase: GetTopAlbumsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _topAlbums = MutableLiveData<Event<List<Any>>>()
    val topAlbums: LiveData<Event<List<Any>>> = _topAlbums

    private val _errorMessage = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>> = _errorMessage

    private val _isLoading = MutableLiveData<Event<Boolean>>()
    val isLoading: LiveData<Event<Boolean>> = _isLoading

    private var selectedArtist: String? = null

    init {
        savedStateHandle.get<String>(Constants.PARAM_ARTIST_ID)?.let { artist ->
            selectedArtist = artist
        }
    }

    fun getTopAlbums(artist: String?) {
        getTopAlbumsUseCase(artist).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _topAlbums.postValue(Event(result.data ?: emptyList()))
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