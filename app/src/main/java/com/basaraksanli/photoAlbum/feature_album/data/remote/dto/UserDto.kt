package com.basaraksanli.photoAlbum.feature_album.data.remote.dto

import com.basaraksanli.photoAlbum.feature_album.domain.model.User


data class UserDto(
    val address: AddressDto,
    val company: CompanyDto,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
) {
    fun toUser(): User {
        return User(
            address = address.toAddress(),
            company = company.toCompany(),
            email = email,
            id = id,
            name = name,
            phone = phone,
            username = username,
            website = website
        )
    }
}