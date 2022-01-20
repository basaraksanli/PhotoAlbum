package com.basaraksanli.photoAlbum.feature_album.presentation.photolistscreen

import com.basaraksanli.photoAlbum.feature_album.domain.model.PhotoListItem


sealed class PhotoListScreenState
{
    object PhotoListScreenLoading : PhotoListScreenState()
    class PhotoListScreenLoaded(val photoList : List<PhotoListItem>) : PhotoListScreenState()
    class PhotoListNetworkError(val loadError: String) : PhotoListScreenState()
}


