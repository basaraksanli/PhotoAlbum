package com.basaraksanli.photoAlbum.feature_album.presentation.photolistscreen

sealed class PhotoListScreenEvent {
    object LoadPhotos : PhotoListScreenEvent()
}