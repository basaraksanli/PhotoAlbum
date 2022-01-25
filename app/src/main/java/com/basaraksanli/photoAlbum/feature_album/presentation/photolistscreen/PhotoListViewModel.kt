package com.basaraksanli.photoAlbum.feature_album.presentation.photolistscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basaraksanli.photoAlbum.feature_album.domain.model.Photo
import com.basaraksanli.photoAlbum.feature_album.domain.use_case.AlbumUseCases
import com.basaraksanli.photoAlbum.feature_album.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PhotoListViewModel @Inject constructor(
    private val useCases: AlbumUseCases,
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf<PhotoListScreenState>(PhotoListScreenState.PhotoListScreenLoading)
    var state: State<PhotoListScreenState> = _state

    val albumTitle = stateHandle.get<String>("albumTitle")
    private val albumId = stateHandle.get<Int>("albumId")
    val userName = stateHandle.get<String>("userName")

    init {
        onEvent(PhotoListScreenEvent.LoadPhotos)
    }
    fun onEvent(event: PhotoListScreenEvent) {
        when (event) {
            is PhotoListScreenEvent.LoadPhotos -> {
                loadPhotoList(albumId!!)
            }
        }
    }
    private fun loadPhotoList(albumId: Int) {
        viewModelScope.launch {
            when (val result = useCases.getPhotoList(albumId = albumId)) {
                is ApiResult.Success ->{
                    val photoEntries = result.data!!.mapIndexed { _, entry ->
                        Photo(
                            albumId = entry.albumId,
                            id = entry.id,
                            thumbnailUrl = entry.thumbnailUrl,
                            title = entry.title,
                            url = entry.url,
                        )
                    }
                    _state.value = PhotoListScreenState.PhotoListScreenLoaded(photoList = photoEntries)
                }
                is ApiResult.Error -> {
                    if (result.message != null) {
                        _state.value = PhotoListScreenState.PhotoListNetworkError(result.message)
                        Timber.e(Throwable(result.message))
                    } else {
                        _state.value =
                            PhotoListScreenState.PhotoListNetworkError("Unknown Error has occurred.")
                        Timber.e(Throwable("Unknown Error has occurred."))
                    }
                }
            }
        }
    }
}