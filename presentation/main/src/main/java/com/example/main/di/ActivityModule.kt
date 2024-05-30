package com.example.main.di

import com.example.activity.repository.ActivityRepository
import com.example.activity.usecase.GetActivityByDateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ActivityModule {

    @Provides
    fun provideGetActivityByDateUseCase(activityRepository: ActivityRepository): GetActivityByDateUseCase {
        return GetActivityByDateUseCase(activityRepository)
    }

}