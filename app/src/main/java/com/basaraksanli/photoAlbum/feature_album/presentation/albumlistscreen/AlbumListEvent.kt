package com.basaraksanli.photoAlbum.feature_album.presentation.albumlistscreen

sealed class AlbumListEvent {
    object LoadUsers : AlbumListEvent()
    object LoadAlbums: AlbumListEvent()
}