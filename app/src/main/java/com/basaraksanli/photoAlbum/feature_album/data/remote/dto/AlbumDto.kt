package com.basaraksanli.photoAlbum.feature_album.data.remote.dto

import com.basaraksanli.photoAlbum.feature_album.domain.model.Album

data class AlbumDto(
    val id: Int,
    val title: String,
    val userId: Int
) {
    fun toAlbum(): Album {
        return Album(
            id = id,
            title = title,
            userId = userId
        )
    }
}