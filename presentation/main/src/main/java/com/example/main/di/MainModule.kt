package com.example.main.di

import com.example.session.repository.SessionRepository
import com.example.session.usecase.GetUserProfileUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class MainModule {

    @Provides
    fun provideGetUserProfileUseCase(sessionRepository: SessionRepository): GetUserProfileUseCase {
        return GetUserProfileUseCase(sessionRepository)
    }
}