package com.basaraksanli.photoAlbum.data.remote

import com.basaraksanli.photoAlbum.data.remote.responses.AlbumList
import com.basaraksanli.photoAlbum.data.remote.responses.PhotoList
import com.basaraksanli.photoAlbum.data.remote.responses.PhotoListItem
import com.basaraksanli.photoAlbum.data.remote.responses.UserList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AlbumApi {

    @GET(value = "users")
    suspend fun getUserList(): UserList

    @GET(value = "albums")
    suspend fun getAlbumList(): AlbumList

    @GET(value = "albums/{albumId}/photos")
    suspend fun getPhotoList(
        @Path(value = "albumId") albumId: Int
    ): PhotoList

    @GET(value = "photos/{photoId}")
    suspend fun getPhotoInfo(
        @Path(value = "photoId") albumId: Int
    ): PhotoListItem

}