package com.basaraksanli.photoAlbum.feature_album.data.repository

import com.basaraksanli.photoAlbum.feature_album.data.remote.AlbumApi
import com.basaraksanli.photoAlbum.feature_album.domain.model.AlbumList
import com.basaraksanli.photoAlbum.feature_album.domain.model.PhotoList
import com.basaraksanli.photoAlbum.feature_album.domain.model.UserList
import com.basaraksanli.photoAlbum.feature_album.domain.repository.AlbumRepository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val api: AlbumApi) : AlbumRepository{
    override suspend fun getUserList(): UserList {
        return api.getUserList()
    }

    override suspend fun getAlbumList(): AlbumList {
        return api.getAlbumList()
    }

    override suspend fun getPhotoList(albumId: Int): PhotoList {
        return api.getPhotoList(albumId = albumId)
    }
}