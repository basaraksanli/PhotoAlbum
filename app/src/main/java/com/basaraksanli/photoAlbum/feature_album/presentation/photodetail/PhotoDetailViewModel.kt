package com.basaraksanli.photoAlbum.feature_album.presentation.photodetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(stateHandle: SavedStateHandle) : ViewModel() {
    private val photoUrl = stateHandle.get<String>("photoUrl")
    private val photoTitle = stateHandle.get<String>("photoTitle")
    private val albumTitle = stateHandle.get<String>("albumTitle")


    private val _state =
        mutableStateOf<PhotoDetailScreenState>(PhotoDetailScreenState.PhotoDetailScreenLoading)
    var state: State<PhotoDetailScreenState> = _state

    init {
        if (photoUrl != null && photoTitle != null && albumTitle != null)
            _state.value =
                PhotoDetailScreenState.PhotoDetailScreenLoaded(photoUrl, albumTitle, photoTitle)
        else
            _state.value = PhotoDetailScreenState.PhotoDetailScreenError
    }

}