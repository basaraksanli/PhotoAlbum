package com.basaraksanli.photoAlbum.feature_album.presentation.albumlistscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basaraksanli.photoAlbum.feature_album.domain.model.AlbumListItem
import com.basaraksanli.photoAlbum.feature_album.domain.model.UserListItem
import com.basaraksanli.photoAlbum.feature_album.domain.use_case.AlbumUseCases
import com.basaraksanli.photoAlbum.feature_album.util.ApiResult
import com.basaraksanli.photoAlbum.feature_album.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(
    private val useCases: AlbumUseCases,
) : ViewModel() {

    private val _state = mutableStateOf(AlbumListState())
    var state : State<AlbumListState> =_state


    init {
        loadUserList()
        loadAlbumList()
    }

    fun onEvent(event: AlbumListEvent){
        when(event){
            is AlbumListEvent.LoadAlbums ->{
                loadAlbumList()
            }
            is AlbumListEvent.LoadUsers ->{
                if(_state.value.loadError != "" && !_state.value.isUsersLoading)
                    loadAlbumList()
            }
        }
    }

    private fun loadUserList() {
        viewModelScope.launch {
            when (val result = useCases.getUserList()) {
                is ApiResult.Success -> {
                    val userEntries = result.data!!.mapIndexed { _, entry ->
                        UserListItem(
                            address = entry.address,
                            company = entry.company,
                            email = entry.email,
                            id = entry.id,
                            name = entry.name,
                            phone = entry.phone,
                            username = entry.username,
                            website = entry.website
                        )
                    }
                    _state.value = state.value.copy(
                        loadError = "",
                        isUsersLoading = false,
                        userList = userEntries
                    )
                }
                is ApiResult.Error -> {
                    _state.value = state.value.copy(
                        loadError = result.message!!,
                        isUsersLoading = false,
                        isAlbumsLoading = false,
                    )
                }
            }
        }
    }

    private fun loadAlbumList() {
        viewModelScope.launch {
            when (val result = useCases.getAlbumList()) {
                is ApiResult.Success -> {
                    val tempThumbnailList: ArrayList<List<Int>> = arrayListOf()
                    val albumEntries = result.data!!.mapIndexed { _, entry ->
                        AlbumListItem(entry.id, entry.title, entry.userId)
                    }
                    val tempAlbum =  albumEntries.groupBy {
                        it.userId
                    }

                    for (i: Int in 0..tempAlbum.size) {
                        tempThumbnailList.add(Constants.THUMBNAIL_IMAGE_LIST.shuffled())
                    }

                    _state.value = state.value.copy(
                        loadError = "",
                        isAlbumsLoading = false,
                        albumList = tempAlbum,
                        thumbnailList = tempThumbnailList
                    )
                }
                is ApiResult.Error -> {
                    _state.value = state.value.copy(
                        loadError = result.message!!,
                        isUsersLoading = false,
                        isAlbumsLoading = false,
                    )
                }
            }
        }
    }

    fun getImage(index: Int, imageList: List<Int>): Int {
        return if (index > imageList.size - 1)
            imageList[0]
        else
            imageList[index]
    }
}
