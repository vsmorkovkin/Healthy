package com.example.videos.di

import com.example.videos.repository.VideosRepository
import com.example.videos.repository.VideosRepositoryImpl
import com.example.videos.service.VideosService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
abstract class VideosDataModule {

    @Binds
    abstract fun bindVideosRepository(impl: VideosRepositoryImpl): VideosRepository

    companion object {

        private const val baseUrl = "https://healthy-2d32b-default-rtdb.firebaseio.com/"

        @Provides
        fun provideVideosService(): VideosService {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(VideosService::class.java)
        }
    }
}