package com.basaraksanli.photoAlbum.feature_album.domain.use_case

import com.basaraksanli.photoAlbum.feature_album.domain.model.User
import com.basaraksanli.photoAlbum.feature_album.domain.repository.AlbumRepository
import com.basaraksanli.photoAlbum.feature_album.util.ApiResult
import javax.inject.Inject

class GetUserList @Inject constructor(
    private val repository: AlbumRepository){
    suspend operator fun invoke(): ApiResult<List<User>> {
        val response = try {
            repository.getUserList()
        } catch (e: Exception) {
            return ApiResult.Error("An unknown error occurred.")
        }
        return ApiResult.Success(response);
    }
}