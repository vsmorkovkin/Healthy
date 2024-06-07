package com.example.onboarding.usecase

import com.example.onboarding.repository.OnboardingRepository

class GetOnboardingCompleteStatusUseCase(
    private val onboardingRepository: OnboardingRepository
) {
    operator fun invoke(): Boolean {
        return onboardingRepository.getOnboardingCompleteStatus()
    }
}