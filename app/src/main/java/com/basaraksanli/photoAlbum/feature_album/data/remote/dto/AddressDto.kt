package com.basaraksanli.photoAlbum.feature_album.data.remote.dto

data class AddressDto(
    val city: String,
    val geo: GeoDto,
    val street: String,
    val suite: String,
    val zipcode: String
)