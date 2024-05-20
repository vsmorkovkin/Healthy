package com.example.session.di

import com.example.session.SessionService
import com.example.session.SessionServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SessionModule {

    @Binds
    abstract fun provideAccountService(impl: SessionServiceImpl): SessionService

}