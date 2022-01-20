package com.basaraksanli.photoAlbum.feature_album.presentation.albumlistscreen

import com.basaraksanli.photoAlbum.feature_album.domain.model.AlbumListItem
import com.basaraksanli.photoAlbum.feature_album.domain.model.UserListItem

data class AlbumListState(
    val isUsersLoading : Boolean = true,
    val isAlbumsLoading : Boolean = true,
    val userList : List<UserListItem> = listOf(),
    val albumList : Map<Int, List<AlbumListItem>> = mapOf(),
    val thumbnailList : List<List<Int>> = mutableListOf(),
    val loadError : String = "",

)
