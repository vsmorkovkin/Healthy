package com.example.onboarding.usecase

import com.example.onboarding.repository.OnboardingRepository

class UpdateOnboardingCompleteStatusUseCase(
    private val onboardingRepository: OnboardingRepository
) {
    operator fun invoke(isCompleted: Boolean) {
        return onboardingRepository.updateOnboardingCompleteStatus(isCompleted)
    }
}