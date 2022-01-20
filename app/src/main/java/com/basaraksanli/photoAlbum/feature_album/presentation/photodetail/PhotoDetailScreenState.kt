package com.basaraksanli.photoAlbum.feature_album.presentation.photodetail


sealed class PhotoDetailScreenState
{
    object PhotoDetailScreenLoading: PhotoDetailScreenState()
    class PhotoDetailScreenLoaded(val photoUrl: String, val albumTitle: String, val photoTitle : String) : PhotoDetailScreenState()
    object PhotoDetailScreenError: PhotoDetailScreenState()
}


