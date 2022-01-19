package com.basaraksanli.photoAlbum.data.remote.responses

data class PhotoListItem(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)