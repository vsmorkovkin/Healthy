package com.example.onboarding

import android.content.Context
import com.example.onboarding.repository.OnboardingRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : OnboardingRepository {

    companion object {
        private const val ONBOARDING_SHARED_PREFS = "ONBOARDING_SHARED_PREFS"
        private const val ONBOARDING_COMPLETE_STATUS = "ONBOARDING_COMPLETE_STATUS"
    }

    private val sharedPreferences = context.getSharedPreferences(ONBOARDING_SHARED_PREFS, Context.MODE_PRIVATE)

    override fun getOnboardingCompleteStatus(): Boolean {
        return sharedPreferences.getBoolean(ONBOARDING_COMPLETE_STATUS, false)
    }

    override fun updateOnboardingCompleteStatus(isCompleted: Boolean) {
        sharedPreferences.edit()
            .putBoolean(ONBOARDING_COMPLETE_STATUS, isCompleted)
            .apply()
    }

}