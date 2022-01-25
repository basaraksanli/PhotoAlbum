package com.basaraksanli.photoAlbum.feature_album.data.remote.dto

import com.basaraksanli.photoAlbum.feature_album.domain.model.Company

data class CompanyDto(
    val bs: String,
    val catchPhrase: String,
    val name: String
){
    fun toCompany() : Company {
        return Company(
            bs= bs,
            catchPhrase = catchPhrase,
            name = name
        )
    }
}