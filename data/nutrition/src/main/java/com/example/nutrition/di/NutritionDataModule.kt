package com.example.nutrition.di

import com.example.nutrition.repository.NutritionRepository
import com.example.nutrition.repository.NutritionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NutritionDataModule {

    @Binds
    abstract fun bindNutritionRepository(impl: NutritionRepositoryImpl): NutritionRepository
}