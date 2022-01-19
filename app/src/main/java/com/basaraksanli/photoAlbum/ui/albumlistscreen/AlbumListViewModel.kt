package com.basaraksanli.photoAlbum.ui.albumlistscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basaraksanli.photoAlbum.data.remote.responses.AlbumListItem
import com.basaraksanli.photoAlbum.data.remote.responses.UserListItem
import com.basaraksanli.photoAlbum.repository.AlbumRepository
import com.basaraksanli.photoAlbum.util.ApiResult
import com.basaraksanli.photoAlbum.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(
    private val repository: AlbumRepository,
) : ViewModel() {

    var userList = mutableStateOf<List<UserListItem>>(listOf())
    var albumList = mutableStateOf<Map<Int, List<AlbumListItem>>>(mapOf())
    var thumbnailList = mutableStateOf<List<List<Int>>>(mutableListOf())
    var loadError = mutableStateOf("")
    var isUserLoading = mutableStateOf(false)
    var isAlbumLoading = mutableStateOf(false)



    init {
        loadUserList()
        loadAlbumList()
    }

    private fun loadUserList() {
        viewModelScope.launch {
            isUserLoading.value = true
            when (val result = repository.gelUserList()) {
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
                    loadError.value = ""
                    isUserLoading.value = false
                    userList.value = userEntries
                }
                is ApiResult.Error -> {
                    loadError.value = result.message!!
                    isUserLoading.value = false
                }
            }
        }
    }

    private fun loadAlbumList() {
        viewModelScope.launch {
            isAlbumLoading.value = true
            when (val result = repository.getAlbumList()) {
                is ApiResult.Success -> {
                    val tempThumbnailList: ArrayList<List<Int>> = arrayListOf()
                    val albumEntries = result.data!!.mapIndexed { _, entry ->
                        AlbumListItem(entry.id, entry.title, entry.userId)
                    }
                    loadError.value = ""
                    isAlbumLoading.value = false
                    albumList.value += albumEntries.groupBy {
                        it.userId
                    }
                    for (i: Int in 0..albumList.value.size) {
                        tempThumbnailList.add(Constants.THUMBNAIL_IMAGE_LIST.shuffled())
                    }
                    thumbnailList.value = tempThumbnailList

                }
                is ApiResult.Error -> {
                    loadError.value = result.message!!
                    isAlbumLoading.value = false
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
