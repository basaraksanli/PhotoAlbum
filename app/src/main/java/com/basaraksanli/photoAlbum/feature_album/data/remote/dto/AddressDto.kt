package com.basaraksanli.photoAlbum.feature_album.data.remote.dto

import com.basaraksanli.photoAlbum.feature_album.domain.model.Address

data class AddressDto(
    val city: String,
    val geo: GeoDto,
    val street: String,
    val suite: String,
    val zipcode: String
) {
    fun toAddress(): Address {
        return Address(
            city = city,
            geo = geo.toGeo(),
            street = street,
            suite = suite,
            zipcode = zipcode
        )
    }
}