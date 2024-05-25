package com.example.session.di

import com.example.session.SessionService
import com.example.session.SessionServiceImpl
import com.example.session.repository.SessionRepository
import com.example.session.repository.SessionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SessionModule {

    @Binds
    abstract fun bindAccountService(impl: SessionServiceImpl): SessionService

    @Binds
    abstract fun bindSessionRepository(impl: SessionRepositoryImpl): SessionRepository

}