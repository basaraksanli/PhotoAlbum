package com.basaraksanli.photoAlbum.feature_album.presentation.photodetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(stateHandle: SavedStateHandle) : ViewModel() {
    val photoUrl = stateHandle.get<String>("photoUrl")
    val photoTitle = stateHandle.get<String>("photoTitle")
    val albumTitle = stateHandle.get<String>("albumTitle")

}