package com.basaraksanli.photoAlbum.feature_album.domain.model


data class User(
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)