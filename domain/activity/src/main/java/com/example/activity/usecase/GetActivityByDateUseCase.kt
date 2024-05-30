package com.example.activity.usecase

import com.example.activity.entity.ActivityEntity
import com.example.activity.repository.ActivityRepository

class GetActivityByDateUseCase(
    private val activityRepository: ActivityRepository
) {
    suspend operator fun invoke(date: String): ActivityEntity? {
        return activityRepository.getActivityByDate(date)
    }
}