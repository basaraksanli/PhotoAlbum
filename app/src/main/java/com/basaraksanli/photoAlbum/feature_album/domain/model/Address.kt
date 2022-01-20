package com.basaraksanli.photoAlbum.feature_album.domain.model

data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
)