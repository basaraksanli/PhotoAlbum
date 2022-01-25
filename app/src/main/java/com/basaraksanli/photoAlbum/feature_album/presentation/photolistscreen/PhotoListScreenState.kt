package com.basaraksanli.photoAlbum.feature_album.presentation.photolistscreen

import com.basaraksanli.photoAlbum.feature_album.domain.model.Photo


sealed class PhotoListScreenState
{
    object PhotoListScreenLoading : PhotoListScreenState()
    class PhotoListScreenLoaded(val photoList : List<Photo>) : PhotoListScreenState()
    class PhotoListNetworkError(val loadError: String) : PhotoListScreenState()
}


