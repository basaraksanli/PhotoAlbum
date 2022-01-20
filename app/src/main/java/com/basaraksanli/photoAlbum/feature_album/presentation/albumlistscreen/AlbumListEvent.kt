package com.basaraksanli.photoAlbum.feature_album.presentation.albumlistscreen

import com.basaraksanli.photoAlbum.feature_album.domain.model.UserListItem

sealed class AlbumListEvent {
    object LoadUsers : AlbumListEvent()
    class LoadAlbums(val userList: List<UserListItem>): AlbumListEvent()
}