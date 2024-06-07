package com.example.healthy.navigation.di

import com.example.auth.navigation.AuthDeeplinkProcessor
import com.example.common.navigation.DeeplinkProcessor
import com.example.main.navigation.MainDeeplinkProcessor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
interface DeeplinkProcessorModule {

    @Binds
    @IntoSet
    fun bindAuthProcessor(
        authDeeplinkProcessor: AuthDeeplinkProcessor
    ): DeeplinkProcessor

    @Binds
    @IntoSet
    fun bindMainProcessor(
        mainDeeplinkProcessor: MainDeeplinkProcessor
    ): DeeplinkProcessor
}