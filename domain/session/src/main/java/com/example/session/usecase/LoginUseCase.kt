package com.example.session.usecase

import com.example.session.repository.SessionRepository

class LoginUseCase(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(email: String, password: String) {
        sessionRepository.login(email, password)
    }
}