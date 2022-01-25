package com.basaraksanli.photoAlbum.feature_album.domain.model

data class Photo(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)