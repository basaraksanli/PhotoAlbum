package com.basaraksanli.photoAlbum.ui.photolistscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.basaraksanli.photoAlbum.data.remote.responses.PhotoList
import com.basaraksanli.photoAlbum.data.remote.responses.PhotoListItem
import com.basaraksanli.photoAlbum.repository.AlbumRepository
import com.basaraksanli.photoAlbum.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoListViewModel @Inject constructor(
    private val repository: AlbumRepository,
    private val stateHandle: SavedStateHandle
) : ViewModel() {
    var photoList = mutableStateOf<List<PhotoListItem>>(listOf())
    val albumTitle = stateHandle.get<String>("albumTitle")

    suspend fun loadPhotoList(albumId: Int): ApiResult<PhotoList> {
        return repository.getPhotoList(albumId = albumId)
    }

    fun setPhotoList(newPhotoList: List<PhotoListItem>){
        photoList.value = newPhotoList
    }
}