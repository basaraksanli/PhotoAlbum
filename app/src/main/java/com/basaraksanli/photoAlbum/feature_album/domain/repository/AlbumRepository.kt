package com.basaraksanli.photoAlbum.feature_album.domain.repository

import com.basaraksanli.photoAlbum.feature_album.domain.model.Album
import com.basaraksanli.photoAlbum.feature_album.domain.model.Photo
import com.basaraksanli.photoAlbum.feature_album.domain.model.User


interface AlbumRepository {
    suspend fun getUserList(): List<User>

    suspend fun getAlbumList(): List<Album>

    suspend fun getPhotoList(albumId: Int): List<Photo>
}