package com.basaraksanli.photoAlbum.feature_album.domain.use_case

import com.basaraksanli.photoAlbum.feature_album.domain.model.PhotoList
import com.basaraksanli.photoAlbum.feature_album.domain.repository.AlbumRepository
import com.basaraksanli.photoAlbum.feature_album.util.ApiResult
import javax.inject.Inject

class GetPhotoList @Inject constructor(
    private val repository: AlbumRepository
) {
    suspend operator fun invoke(albumId: Int): ApiResult<PhotoList> {
        val response = try {
            repository.getPhotoList(albumId)
        } catch (e: Exception) {
            return ApiResult.Error("An unknown error occurred.")
        }
        return ApiResult.Success(response);
    }
}