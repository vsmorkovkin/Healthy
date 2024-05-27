package com.example.onboarding.repository

interface OnboardingRepository {
    fun getOnboardingCompleteStatus(): Boolean
    fun updateOnboardingCompleteStatus(isCompleted: Boolean)
}