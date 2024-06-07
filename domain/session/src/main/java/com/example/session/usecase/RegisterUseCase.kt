package com.example.session.usecase

import com.example.session.entity.UserRegisterEntity
import com.example.session.repository.SessionRepository

class RegisterUseCase(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(userRegisterEntity: UserRegisterEntity) {
        sessionRepository.register(userRegisterEntity)
    }
}