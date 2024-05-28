package com.example.auth.fragments.registration.di

import com.example.session.repository.SessionRepository
import com.example.session.usecase.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RegistrationModule {

    @Provides
    fun provideRegisterUseCase(sessionRepository: SessionRepository): RegisterUseCase {
        return RegisterUseCase(sessionRepository)
    }

}