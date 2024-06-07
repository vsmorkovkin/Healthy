package com.example.main.fragments.profile.di

import com.example.session.repository.SessionRepository
import com.example.session.usecase.LogoutUseCase
import com.example.session.usecase.SetUserProfileImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ProfileModule {

    @Provides
    fun provideSetUserProfileImageUseCase(sessionRepository: SessionRepository): SetUserProfileImageUseCase {
        return SetUserProfileImageUseCase(sessionRepository)
    }

    @Provides
    fun provideLogoutUseCase(sessionRepository: SessionRepository): LogoutUseCase {
        return LogoutUseCase(sessionRepository)
    }
}