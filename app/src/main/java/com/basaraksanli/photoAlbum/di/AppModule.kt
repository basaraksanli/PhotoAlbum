package com.basaraksanli.photoAlbum.di

import com.basaraksanli.photoAlbum.data.remote.AlbumApi
import com.basaraksanli.photoAlbum.repository.AlbumRepository
import com.basaraksanli.photoAlbum.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAlbumRepository(
        api: AlbumApi
    ) = AlbumRepository(api)

    @Singleton
    @Provides
    fun provideAlbumApi() : AlbumApi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(AlbumApi::class.java)
    }
}