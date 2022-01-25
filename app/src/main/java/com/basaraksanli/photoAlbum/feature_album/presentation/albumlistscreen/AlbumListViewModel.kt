package com.basaraksanli.photoAlbum.feature_album.presentation.albumlistscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basaraksanli.photoAlbum.feature_album.domain.model.Album
import com.basaraksanli.photoAlbum.feature_album.domain.model.User
import com.basaraksanli.photoAlbum.feature_album.domain.use_case.AlbumUseCases
import com.basaraksanli.photoAlbum.feature_album.util.ApiResult
import com.basaraksanli.photoAlbum.feature_album.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(
    private val useCases: AlbumUseCases,
) : ViewModel() {

    private val _state = mutableStateOf<AlbumListState>(AlbumListState.AlbumListScreenLoading)
    var state: State<AlbumListState> = _state

    init {
        onEvent(AlbumListEvent.LoadUsers)
    }
    fun onEvent(event: AlbumListEvent) {
        when (event) {
            is AlbumListEvent.LoadUsers -> {
                loadUserList()
            }
            is AlbumListEvent.LoadAlbums -> {
                loadAlbumList(event.userList)
            }
        }
    }

    private fun loadUserList() {
        viewModelScope.launch {
            when (val result = useCases.getUserList()) {
                is ApiResult.Success -> {
                    val userEntries = result.data!!.mapIndexed { _, entry ->
                        User(
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
                    _state.value = AlbumListState.AlbumListScreenUsersLoaded(userList = userEntries)
                    onEvent(AlbumListEvent.LoadAlbums(userEntries))
                }
                is ApiResult.Error -> {
                    if (result.message != null) {
                        _state.value = AlbumListState.AlbumListScreenNetworkError(result.message)
                        Timber.e(Throwable(result.message))
                    } else {
                        _state.value =
                            AlbumListState.AlbumListScreenNetworkError("Unknown Error has occurred.")
                        Timber.e(Throwable("Unknown Error has occurred."))
                    }
                }
            }
        }
    }

    private fun loadAlbumList(userList: List<User>) {
        viewModelScope.launch {
            when (val result = useCases.getAlbumList()) {
                is ApiResult.Success -> {
                    val tempThumbnailList: ArrayList<List<Int>> = arrayListOf()
                    val albumEntries = result.data!!.mapIndexed { _, entry ->
                        Album(entry.id, entry.title, entry.userId)
                    }
                    val tempAlbum = albumEntries.groupBy {
                        it.userId
                    }

                    for (i: Int in 0..tempAlbum.size) {
                        tempThumbnailList.add(Constants.THUMBNAIL_IMAGE_LIST.shuffled())
                    }
                    _state.value = AlbumListState.AlbumListScreenLoaded(userList = userList,tempAlbum, tempThumbnailList)
                }
                is ApiResult.Error -> {
                    if (result.message != null) {
                        _state.value = AlbumListState.AlbumListScreenNetworkError(result.message)
                        Timber.e(Throwable(result.message))
                    } else {
                        _state.value =
                            AlbumListState.AlbumListScreenNetworkError("Unknown Error has occurred.")
                        Timber.e(Throwable("Unknown Error has occurred."))
                    }
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
