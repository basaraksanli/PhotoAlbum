package com.basaraksanli.photoAlbum.feature_album.presentation.photolistscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.basaraksanli.photoAlbum.feature_album.domain.model.PhotoList
import com.basaraksanli.photoAlbum.feature_album.domain.model.PhotoListItem
import com.basaraksanli.photoAlbum.feature_album.domain.use_case.AlbumUseCases
import com.basaraksanli.photoAlbum.feature_album.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoListViewModel @Inject constructor(
    private val useCases: AlbumUseCases,
    private val stateHandle: SavedStateHandle
) : ViewModel() {
    var photoList = mutableStateOf<List<PhotoListItem>>(listOf())
    val albumTitle = stateHandle.get<String>("albumTitle")

    suspend fun loadPhotoList(albumId: Int): ApiResult<PhotoList> {
        return useCases.getPhotoList(albumId = albumId)
    }

    fun setPhotoList(newPhotoList: List<PhotoListItem>){
        photoList.value = newPhotoList
    }
}