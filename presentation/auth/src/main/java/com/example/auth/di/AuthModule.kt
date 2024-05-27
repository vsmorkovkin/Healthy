package com.example.auth.di

import com.example.onboarding.repository.OnboardingRepository
import com.example.onboarding.usecase.GetOnboardingCompleteStatusUseCase
import com.example.onboarding.usecase.UpdateOnboardingCompleteStatusUseCase
import com.example.session.repository.SessionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class AuthModule {

    @Provides
    fun provideGetOnboardingCompleteStatusUseCase(onboardingRepository: OnboardingRepository): GetOnboardingCompleteStatusUseCase {
        return GetOnboardingCompleteStatusUseCase(onboardingRepository)
    }


    @Provides
    fun provideUpdateOnboardingCompleteStatusUseCase(onboardingRepository: OnboardingRepository): UpdateOnboardingCompleteStatusUseCase {
        return UpdateOnboardingCompleteStatusUseCase(onboardingRepository)
    }

}