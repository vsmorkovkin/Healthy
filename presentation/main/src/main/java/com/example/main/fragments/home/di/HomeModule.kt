package com.example.main.fragments.home.di

import com.example.activity.repository.ActivityRepository
import com.example.activity.usecase.GetActivityByDateUseCase
import com.example.activity.usecase.GetActivityWithNutritionByDateUseCase
import com.example.activity.usecase.UpdateActivityUseCase
import com.example.nutrition.repository.NutritionRepository
import com.example.nutrition.usecase.GetNutritionByDateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class HomeModule {

    @Provides
    fun provideUpdateActivityUseCase(activityRepository: ActivityRepository): UpdateActivityUseCase {
        return UpdateActivityUseCase(activityRepository)
    }

    @Provides
    fun provideGetActivityByDateUseCase(activityRepository: ActivityRepository): GetActivityByDateUseCase {
        return GetActivityByDateUseCase(activityRepository)
    }

    @Provides
    fun provideGetActivityWithNutritionByDateUseCase(
        getActivityByDateUseCase: GetActivityByDateUseCase,
        getNutritionByDateUseCase: GetNutritionByDateUseCase
    ): GetActivityWithNutritionByDateUseCase {
        return GetActivityWithNutritionByDateUseCase(
            getActivityByDateUseCase,
            getNutritionByDateUseCase
        )
    }

    @Provides
    fun provideGetNutritionByDateUseCase(nutritionRepository: NutritionRepository): GetNutritionByDateUseCase {
        return GetNutritionByDateUseCase(nutritionRepository)
    }

}