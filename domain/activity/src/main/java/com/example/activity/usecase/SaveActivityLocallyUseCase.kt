package com.example.activity.usecase

import com.example.activity.entity.ActivityEntity
import com.example.activity.repository.ActivityRepository

class SaveActivityLocallyUseCase(
    private val activityRepository: ActivityRepository
) {
    operator fun invoke(activityEntity: ActivityEntity) {
        activityRepository.saveActivityLocally(activityEntity)
    }
}