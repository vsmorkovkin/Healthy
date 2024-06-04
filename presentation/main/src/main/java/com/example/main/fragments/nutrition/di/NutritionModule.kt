package com.example.main.fragments.nutrition.di

import com.example.nutrition.repository.NutritionRepository
import com.example.nutrition.usecase.AddMealByDateUseCase
import com.example.nutrition.usecase.DeleteMealByDateUseCase
import com.example.nutrition.usecase.GetNutritionWithMealsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class NutritionModule {

    @Provides
    fun getNutritionWithMealsUseCase(nutritionRepository: NutritionRepository): GetNutritionWithMealsUseCase {
        return GetNutritionWithMealsUseCase(nutritionRepository)
    }

    @Provides
    fun provideAddMealByDateUseCase(nutritionRepository: NutritionRepository): AddMealByDateUseCase {
        return AddMealByDateUseCase(nutritionRepository)
    }

    @Provides
    fun provideDeleteMealByDateUseCase(nutritionRepository: NutritionRepository): DeleteMealByDateUseCase {
        return DeleteMealByDateUseCase(nutritionRepository)
    }

}