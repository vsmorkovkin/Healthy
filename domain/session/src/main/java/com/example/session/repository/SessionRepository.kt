package com.example.session.repository

import com.example.session.entity.UserProfileEntity
import com.example.session.entity.UserRegisterEntity

interface SessionRepository {
    suspend fun login(email: String, password: String)
    suspend fun logout()
    suspend fun register(userRegisterEntity: UserRegisterEntity)
    suspend fun getUserProfile(): UserProfileEntity
}