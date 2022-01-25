package com.basaraksanli.photoAlbum.feature_album.data.remote.dto

import com.basaraksanli.photoAlbum.feature_album.domain.model.Geo

data class GeoDto(
    val lat: String,
    val lng: String
){
    fun toGeo() : Geo{
        return Geo(
            lat = lat,
            lng = lng
        )
    }
}