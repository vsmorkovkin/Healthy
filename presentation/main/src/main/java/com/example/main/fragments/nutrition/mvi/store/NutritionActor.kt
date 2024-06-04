package com.example.main.fragments.nutrition.mvi.store

import android.util.Log
import com.example.common.mvi.MviActor
import com.example.main.fragments.nutrition.model.toUi
import com.example.main.fragments.nutrition.mvi.effect.NutritionEffect
import com.example.main.fragments.nutrition.mvi.intent.NutritionIntent
import com.example.main.fragments.nutrition.mvi.state.NutritionPartialState
import com.example.main.fragments.nutrition.mvi.state.NutritionState
import com.example.main.utils.DateConverter
import com.example.nutrition.entity.MealEntity
import com.example.nutrition.usecase.AddMealByDateUseCase
import com.example.nutrition.usecase.DeleteMealByDateUseCase
import com.example.nutrition.usecase.GetNutritionWithMealsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NutritionActor @Inject constructor(
    private val getNutritionWithMealsUseCase: GetNutritionWithMealsUseCase,
    private val addMealByDateUseCase: AddMealByDateUseCase,
    private val deleteMealByDateUseCase: DeleteMealByDateUseCase
) :
    MviActor<NutritionPartialState, NutritionIntent, NutritionState, NutritionEffect>() {

    override fun resolve(
        intent: NutritionIntent,
        state: NutritionState
    ): Flow<NutritionPartialState> {
        return when (intent) {
            is NutritionIntent.DateReceivedFromArgs -> loadDate(intent.date)
            is NutritionIntent.GetNutritionWithMealsByDate -> getNutritionWithMealsByDate(intent.date)
            is NutritionIntent.AddMealByDate -> addMealByDate(intent.date, intent.mealEntity)
            is NutritionIntent.DeleteMealByDate -> deleteMealByDate(intent.date, intent.mealDateTimeOfCreation)
            NutritionIntent.OpenAddMealDialog -> showAddMealDialog()
        }
    }

    private fun loadDate(date: String): Flow<NutritionPartialState> = flow {
        emit(NutritionPartialState.DateLoaded(DateConverter.dateEntityToNutritionUi(date)))
    }

    private fun getNutritionWithMealsByDate(date: String): Flow<NutritionPartialState> = flow {
        runCatching {
            getNutritionWithMealsUseCase(date)
        }.fold(
            onSuccess = {
                emit(NutritionPartialState.NutritionWithMealsLoaded(it.toUi()))
            },
            onFailure = {
                Log.d("Nutrition", "getNutritionWithMeals failure: ${it.message}")
            }
        )
    }

    private fun addMealByDate(date: String, mealEntity: MealEntity): Flow<NutritionPartialState> = flow {
        runCatching {
            addMealByDateUseCase(mealEntity, date)
            getNutritionWithMealsUseCase(date)
        }.fold(
            onSuccess = {
                emit(NutritionPartialState.NutritionWithMealsLoaded(it.toUi()))
            },
            onFailure = {
                Log.d("Nutrition", "meal adding failure: ${it.message}")
            }
        )
    }

    private fun deleteMealByDate(date: String, mealDateTimeOfCreation: Long): Flow<NutritionPartialState> = flow {
        runCatching {
            deleteMealByDateUseCase(date, mealDateTimeOfCreation)
            getNutritionWithMealsUseCase(date)
        }.fold(
            onSuccess = {
                emit(NutritionPartialState.NutritionWithMealsLoaded(it.toUi()))
            },
            onFailure = {
                Log.d("Nutrition", "meal deleting failure: ${it.message}")
            }
        )
    }

    private fun showAddMealDialog(): Flow<NutritionPartialState> = flow {
        _effects.emit(NutritionEffect.ShowAddMealDialog)
    }

}