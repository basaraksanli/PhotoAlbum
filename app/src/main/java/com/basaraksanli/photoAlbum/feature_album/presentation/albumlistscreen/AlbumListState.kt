package com.basaraksanli.photoAlbum.feature_album.presentation.albumlistscreen


import com.basaraksanli.photoAlbum.feature_album.domain.model.Album
import com.basaraksanli.photoAlbum.feature_album.domain.model.User


sealed class AlbumListState
{
    object AlbumListScreenLoading : AlbumListState()
    class AlbumListScreenLoaded(val userList: List<User>, val albumList: Map<Int, List<Album>>, val thumbnailList: List<List<Int>>) : AlbumListState()
    class AlbumListScreenUsersLoaded(val userList: List<User>): AlbumListState()
    class AlbumListScreenNetworkError(val loadError: String) : AlbumListState()
}


