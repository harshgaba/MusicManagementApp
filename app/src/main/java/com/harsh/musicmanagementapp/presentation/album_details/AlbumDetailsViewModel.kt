package com.harsh.musicmanagementapp.presentation.album_details

import androidx.lifecycle.*
import com.harsh.musicmanagementapp.domain.model.Album
import com.harsh.musicmanagementapp.domain.use_case.db_action_top_album.TopAlbumActionsUseCase
import com.harsh.musicmanagementapp.domain.use_case.get_album_info.GetAlbumInfoUseCase
import com.harsh.musicmanagementapp.shared.Constants
import com.harsh.musicmanagementapp.shared.Event
import com.harsh.musicmanagementapp.shared.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(
    private val getAlbumInfoUseCase: GetAlbumInfoUseCase,
    private val topAlbumActionsUseCase: TopAlbumActionsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _album = MutableLiveData<Event<Album?>>()
    val album: LiveData<Event<Album?>> = _album

    private val _errorMessage = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>> = _errorMessage

    private val _isLoading = MutableLiveData<Event<Boolean>>()
    val isLoading: LiveData<Event<Boolean>> = _isLoading

    private val _albumSaved = MutableLiveData<Event<Boolean>>()
    val albumSaved: LiveData<Event<Boolean>> = _albumSaved

    private var albumSaveStatus: Boolean = false
    private var selectedArtist: String? = null
    private var selectedAlbumName: String? = null
    private var selectedAlbumId: Int = -1
    private var topAlbum: Album? = null

    init {
        savedStateHandle.get<String>(Constants.PARAM_ARTIST_ID)?.let { artist ->
            selectedArtist = artist
        }
        savedStateHandle.get<Int>(Constants.PARAM_ALBUM_ID)?.let { albumId ->
            selectedAlbumId = albumId
        }
        savedStateHandle.get<String>(Constants.PARAM_ALBUM_NAME)?.let { albumName ->
            selectedAlbumName = albumName
        }


        if (selectedArtist.isNullOrBlank()){
            selectedAlbumName?.let { getAlbumInfoFromDB(it) }
        }
        else getAlbumInfo(selectedArtist, selectedAlbumName)
    }


    fun getAlbumInfo(artist: String?, albumName: String?) {
        getAlbumInfoUseCase(artist, albumName).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    topAlbum = result.data
                    _album.postValue(Event(topAlbum))
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

    fun getAlbumInfoFromDB(name: String) {
        topAlbumActionsUseCase.getAlbumByName(name).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    topAlbum = result.data
                    _album.postValue(Event(topAlbum))
                    _isLoading.postValue(Event(false))
                    albumSaveStatus = true
                    _albumSaved.postValue(Event(albumSaveStatus))
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

    fun insertTopAlbum(album: Album?) {
        album?.let {
            topAlbumActionsUseCase.insertTopAlbum(album).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        albumSaveStatus = true
                        _albumSaved.postValue(Event(albumSaveStatus))
                    }
                    is Resource.Error -> {
                        //TODO , we are not notifying to user for now, may be later show a toast
                    }
                    is Resource.Loading -> {
                        //NO NEED TO HANDLE
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun deleteTopAlbum(album: Album?) {
        album?.let {
            topAlbumActionsUseCase.deleteTopAlbum(album).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        albumSaveStatus = false
                        _albumSaved.postValue(Event(albumSaveStatus))
                    }
                    is Resource.Error -> {
                        //TODO , we are not notifying to user for now, may be later show a toast
                    }
                    is Resource.Loading -> {
                        //NO NEED TO HANDLE
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun manageAlbumLove() {
        if (albumSaveStatus) {
            deleteTopAlbum(topAlbum)
        } else {
            insertTopAlbum(topAlbum)
        }
    }
}