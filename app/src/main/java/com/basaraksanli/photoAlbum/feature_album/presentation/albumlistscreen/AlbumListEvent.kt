package com.basaraksanli.photoAlbum.feature_album.presentation.albumlistscreen

import com.basaraksanli.photoAlbum.feature_album.domain.model.User

sealed class AlbumListEvent {
    object LoadUsers : AlbumListEvent()
    class LoadAlbums(val userList: List<User>): AlbumListEvent()
}