package com.basaraksanli.photoAlbum.feature_album.data.repository

import com.basaraksanli.photoAlbum.feature_album.data.remote.AlbumApi
import com.basaraksanli.photoAlbum.feature_album.domain.model.Album
import com.basaraksanli.photoAlbum.feature_album.domain.model.Photo
import com.basaraksanli.photoAlbum.feature_album.domain.model.User
import com.basaraksanli.photoAlbum.feature_album.domain.repository.AlbumRepository
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val api: AlbumApi) : AlbumRepository{
    override suspend fun getUserList(): List<User> {
        return api.getUserList().map { it.toUser() }
    }

    override suspend fun getAlbumList(): List<Album> {
        return api.getAlbumList().map{ it.toAlbum()}
    }

    override suspend fun getPhotoList(albumId: Int): List<Photo> {
        return api.getPhotoList(albumId = albumId).map { it.toPhoto() }
    }
}