package com.example.activity.usecase

import com.example.activity.entity.ActivityEntity
import com.example.activity.repository.ActivityRepository

class GetLastLocalActivityUseCase(
    private val activityRepository: ActivityRepository
) {
    operator fun invoke(): ActivityEntity? {
        return activityRepository.getLastLocalActivity()
    }
}