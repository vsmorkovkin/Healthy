package com.example.main.fragments.videos.di

import com.example.videos.repository.VideosRepository
import com.example.videos.usecase.GetVideoGroupsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class VideosModule {

    @Provides
    fun provideGetVideoGroupsUseCase(videosRepository: VideosRepository): GetVideoGroupsUseCase {
        return GetVideoGroupsUseCase(videosRepository)
    }
}