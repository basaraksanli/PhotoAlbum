package com.basaraksanli.photoAlbum.di

import com.basaraksanli.photoAlbum.feature_album.data.remote.AlbumApi
import com.basaraksanli.photoAlbum.feature_album.data.repository.AlbumRepositoryImpl
import com.basaraksanli.photoAlbum.feature_album.domain.repository.AlbumRepository
import com.basaraksanli.photoAlbum.feature_album.domain.use_case.AlbumUseCases
import com.basaraksanli.photoAlbum.feature_album.domain.use_case.GetAlbumList
import com.basaraksanli.photoAlbum.feature_album.domain.use_case.GetPhotoList
import com.basaraksanli.photoAlbum.feature_album.domain.use_case.GetUserList
import com.basaraksanli.photoAlbum.feature_album.util.Constants
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
    ) : AlbumRepository = AlbumRepositoryImpl(api)

    @Singleton
    @Provides
    fun provideAlbumApi() : AlbumApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(AlbumApi::class.java)
    }

    @Singleton
    @Provides
    fun provideAlbumUseCases(repository: AlbumRepository): AlbumUseCases{
        return AlbumUseCases(
            getAlbumList = GetAlbumList(repository),
            getPhotoList = GetPhotoList(repository),
            getUserList = GetUserList(repository),
        )
    }
}