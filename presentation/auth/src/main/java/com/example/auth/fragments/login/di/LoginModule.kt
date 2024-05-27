package com.example.auth.fragments.login.di

import com.example.session.repository.SessionRepository
import com.example.session.usecase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class LoginModule {

    @Provides
    fun provideLoginUseCase(sessionRepository: SessionRepository): LoginUseCase {
        return LoginUseCase(sessionRepository)
    }

}