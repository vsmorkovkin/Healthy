package com.example.activity.usecase

import com.example.activity.repository.ActivityRepository

class SetInitialStepsUseCase(
    private val activityRepository: ActivityRepository
) {
    operator fun invoke(date: String, initialSteps: Int) {
        activityRepository.setInitialSteps(date, initialSteps)
    }
}