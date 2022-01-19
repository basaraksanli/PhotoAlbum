package com.basaraksanli.photoAlbum.repository

import com.basaraksanli.photoAlbum.data.remote.AlbumApi
import com.basaraksanli.photoAlbum.data.remote.responses.AlbumList
import com.basaraksanli.photoAlbum.data.remote.responses.PhotoList
import com.basaraksanli.photoAlbum.data.remote.responses.UserList
import com.basaraksanli.photoAlbum.util.ApiResult
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class AlbumRepository @Inject constructor(
    private val api: AlbumApi
) {
    suspend fun gelUserList(): ApiResult<UserList> {
        val response = try {
            api.getUserList()
        } catch (e: Exception) {
            return ApiResult.Error("An unknown error occurred.")
        }
        return ApiResult.Success(response);
    }

    suspend fun getAlbumList(): ApiResult<AlbumList> {
        val response = try {
            api.getAlbumList()
        } catch (e: Exception) {
            return ApiResult.Error("An unknown error occurred.")
        }
        return ApiResult.Success(response);
    }

    suspend fun getPhotoList(albumId: Int): ApiResult<PhotoList> {
        val response = try {
            api.getPhotoList(albumId)
        } catch (e: Exception) {
            return ApiResult.Error("An unknown error occurred.")
        }
        return ApiResult.Success(response);
    }
}