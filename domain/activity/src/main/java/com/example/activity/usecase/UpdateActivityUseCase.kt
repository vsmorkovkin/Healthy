package com.example.activity.usecase

import com.example.activity.entity.ActivityEntity
import com.example.activity.repository.ActivityRepository

class UpdateActivityUseCase(
    private val activityRepository: ActivityRepository
) {
    suspend operator fun invoke(date: String, activityEntity: ActivityEntity) {
        activityRepository.updateActivity(date, activityEntity)
    }
}