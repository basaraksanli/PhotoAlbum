package com.basaraksanli.photoAlbum.feature_album.data.remote

import com.basaraksanli.photoAlbum.feature_album.data.remote.dto.AlbumDto
import com.basaraksanli.photoAlbum.feature_album.data.remote.dto.PhotoDto
import com.basaraksanli.photoAlbum.feature_album.data.remote.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumApi {

    @GET(value = "users")
    suspend fun getUserList(): List<UserDto>

    @GET(value = "albums")
    suspend fun getAlbumList(): List<AlbumDto>

    @GET(value = "albums/{albumId}/photos")
    suspend fun getPhotoList(
        @Path(value = "albumId") albumId: Int
    ): List<PhotoDto>

}