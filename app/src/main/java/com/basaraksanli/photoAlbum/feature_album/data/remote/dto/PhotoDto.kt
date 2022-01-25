package com.basaraksanli.photoAlbum.feature_album.data.remote.dto

import com.basaraksanli.photoAlbum.feature_album.domain.model.Photo

data class PhotoDto(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
) {
    fun toPhoto(): Photo {
        return Photo(
            albumId = albumId,
            id = id,
            thumbnailUrl = thumbnailUrl,
            title = title,
            url = url
        )
    }
}