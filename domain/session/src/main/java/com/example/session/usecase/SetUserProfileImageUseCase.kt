package com.example.session.usecase

import com.example.session.repository.SessionRepository

class SetUserProfileImageUseCase(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(imageUri: String): String? {
        return sessionRepository.setUserProfileImage(imageUri)
    }
}