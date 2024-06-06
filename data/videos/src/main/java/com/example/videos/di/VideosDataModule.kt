package com.example.videos.di

import com.example.videos.repository.VideosRepository
import com.example.videos.repository.VideosRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class VideosDataModule {

    @Binds
    abstract fun bindVideosRepository(impl: VideosRepositoryImpl): VideosRepository

}