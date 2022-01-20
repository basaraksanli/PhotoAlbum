package com.basaraksanli.photoAlbum.feature_album.domain.use_case

import com.basaraksanli.photoAlbum.feature_album.domain.model.AlbumList
import com.basaraksanli.photoAlbum.feature_album.domain.repository.AlbumRepository
import com.basaraksanli.photoAlbum.feature_album.util.ApiResult
import javax.inject.Inject

class GetAlbumList  @Inject constructor(
    private val repository: AlbumRepository
){
    suspend  operator fun invoke(): ApiResult<AlbumList> {
        val response = try {
            repository.getAlbumList()
        } catch (e: Exception) {
            return ApiResult.Error("An unknown error occurred.")
        }
        return ApiResult.Success(response);
    }
}