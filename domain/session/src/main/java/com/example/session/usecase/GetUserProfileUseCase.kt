package com.example.session.usecase

import com.example.session.entity.UserProfileEntity
import com.example.session.repository.SessionRepository

class GetUserProfileUseCase(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(): UserProfileEntity {
        return sessionRepository.getUserProfile()
    }
}