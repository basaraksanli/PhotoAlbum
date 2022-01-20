package com.basaraksanli.photoAlbum.feature_album.presentation.albumlistscreen


import com.basaraksanli.photoAlbum.feature_album.domain.model.AlbumListItem
import com.basaraksanli.photoAlbum.feature_album.domain.model.UserListItem


sealed class AlbumListState
{
    object AlbumListScreenLoading : AlbumListState()
    class AlbumListScreenLoaded(val userList: List<UserListItem>, val albumList: Map<Int, List<AlbumListItem>>, val thumbnailList: List<List<Int>>) : AlbumListState()
    class AlbumListScreenUsersLoaded(val userList: List<UserListItem>): AlbumListState()
    class AlbumListScreenNetworkError(val loadError: String) : AlbumListState()
}


