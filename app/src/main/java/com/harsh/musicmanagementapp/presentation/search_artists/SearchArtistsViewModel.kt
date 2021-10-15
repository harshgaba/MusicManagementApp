package com.harsh.musicmanagementapp.presentation.search_artists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harsh.musicmanagementapp.domain.use_case.search_artist.SearchArtistUseCase
import com.harsh.musicmanagementapp.shared.Event
import com.harsh.musicmanagementapp.shared.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchArtistsViewModel @Inject constructor(
    private val searchArtistUseCase: SearchArtistUseCase
) : ViewModel() {

    private val _artists = MutableLiveData<Event<List<Any>>>()
    val artists: LiveData<Event<List<Any>>> = _artists

    private val _errorMessage = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>> = _errorMessage

    private val _isLoading = MutableLiveData<Event<Boolean>>()
    val isLoading: LiveData<Event<Boolean>> = _isLoading


    fun searchArtists(artist: String?) {
        searchArtistUseCase(artist).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _artists.postValue(Event(result.data ?: emptyList()))
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