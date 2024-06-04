package com.example.main.fragments.nutrition.mvi.store

import android.util.Log
import com.example.common.mvi.MviActor
import com.example.main.fragments.nutrition.model.toUi
import com.example.main.fragments.nutrition.mvi.effect.NutritionEffect
import com.example.main.fragments.nutrition.mvi.intent.NutritionIntent
import com.example.main.fragments.nutrition.mvi.state.NutritionPartialState
import com.example.main.fragments.nutrition.mvi.state.NutritionState
import com.example.nutrition.entity.MealEntity
import com.example.nutrition.usecase.AddMealByDateUseCase
import com.example.nutrition.usecase.GetNutritionWithMealsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NutritionActor @Inject constructor(
    private val addMealByDateUseCase: AddMealByDateUseCase,
    private val getNutritionWithMealsUseCase: GetNutritionWithMealsUseCase,
) :
    MviActor<NutritionPartialState, NutritionIntent, NutritionState, NutritionEffect>() {

    override fun resolve(
        intent: NutritionIntent,
        state: NutritionState
    ): Flow<NutritionPartialState> {
        return when (intent) {
            is NutritionIntent.DateReceivedFromArgs -> loadDate(intent.date)
            is NutritionIntent.GetNutritionWithMealsByDate -> getNutritionWithMealsByDate(intent.date)
            is NutritionIntent.AddMealByDate -> addMealByDate(intent.mealEntity, state.date)
            NutritionIntent.DeleteMealByDate -> deleteMealByDate(state.date)
            NutritionIntent.OpenAddMealDialog -> showAddMealDialog()
        }
    }

    private fun loadDate(date: String): Flow<NutritionPartialState> = flow {
        emit(NutritionPartialState.DateLoaded(date))
    }

    private fun getNutritionWithMealsByDate(date: String): Flow<NutritionPartialState> = flow {
        runCatching {
            getNutritionWithMealsUseCase(date)
        }.fold(
            onSuccess = {
                Log.d("Meal", "getNutritionWithMeals success: ${it.mealsList}")
                emit(NutritionPartialState.NutritionWithMealsLoaded(it.toUi()))
            },
            onFailure = {
                Log.d("Meal", "getNutritionWithMeals failure: ${it.message}")
            }
        )
    }

    private fun addMealByDate(mealEntity: MealEntity, date: String): Flow<NutritionPartialState> = flow {
        runCatching {
            addMealByDateUseCase(mealEntity, date)
            getNutritionWithMealsUseCase(date)
        }.fold(
            onSuccess = {
                Log.d("Meal", "meal added: $mealEntity")
                emit(NutritionPartialState.NutritionWithMealsLoaded(it.toUi()))
            },
            onFailure = {
                Log.d("Meal", "meal adding failure: ${it.message}")
            }
        )
    }

    private fun deleteMealByDate(date: String): Flow<NutritionPartialState> = flow {
        TODO("Not yet implemented")
    }

    private fun showAddMealDialog(): Flow<NutritionPartialState> = flow {
        _effects.emit(NutritionEffect.ShowAddMealDialog)
    }

}