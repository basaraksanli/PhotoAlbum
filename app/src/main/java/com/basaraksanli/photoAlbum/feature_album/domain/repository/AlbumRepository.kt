package com.basaraksanli.photoAlbum.feature_album.domain.repository

import com.basaraksanli.photoAlbum.feature_album.domain.model.AlbumList
import com.basaraksanli.photoAlbum.feature_album.domain.model.PhotoList
import com.basaraksanli.photoAlbum.feature_album.domain.model.UserList


interface AlbumRepository {
    suspend fun getUserList(): UserList

    suspend fun getAlbumList(): AlbumList

    suspend fun getPhotoList(albumId: Int): PhotoList
}