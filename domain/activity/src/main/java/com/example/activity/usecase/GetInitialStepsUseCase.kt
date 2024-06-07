package com.example.activity.usecase

import com.example.activity.repository.ActivityRepository

class GetInitialStepsUseCase(
    private val activityRepository: ActivityRepository
) {
    operator fun invoke(date: String): Int {
        return activityRepository.getInitialSteps(date)
    }
}