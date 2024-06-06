package com.example.main.fragments.activity_by_day.mvi.store

import android.util.Log
import com.example.activity.usecase.GetActivityWithNutritionByDateUseCase
import com.example.common.mvi.MviActor
import com.example.main.fragments.activity_by_day.model.toUi
import com.example.main.fragments.activity_by_day.mvi.effect.ActivityByDayEffect
import com.example.main.fragments.activity_by_day.mvi.intent.ActivityByDayIntent
import com.example.main.fragments.activity_by_day.mvi.state.ActivityByDayPartialState
import com.example.main.fragments.activity_by_day.mvi.state.ActivityByDayState
import com.example.main.utils.DateConverter
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ActivityByDayActor @Inject constructor(
    private val getActivityWithNutritionByDateUseCase: GetActivityWithNutritionByDateUseCase
) : MviActor<ActivityByDayPartialState, ActivityByDayIntent, ActivityByDayState, ActivityByDayEffect>() {

    override fun resolve(
        intent: ActivityByDayIntent,
        state: ActivityByDayState
    ): Flow<ActivityByDayPartialState> {
        return when (intent) {
            is ActivityByDayIntent.GetActivityByDayIntent -> getActivityWithNutritionByDayIntent(intent.date)
            ActivityByDayIntent.OpenSelectDateDialogIntent -> flow {
                _effects.emit(ActivityByDayEffect.OpenSelectDateDialogEffect)
            }
        }
    }

    private fun getActivityWithNutritionByDayIntent(date: String): Flow<ActivityByDayPartialState> = flow {
        emit(
            ActivityByDayPartialState.ActivityByDaySelected(
                DateConverter.dateEntityToActivityByDayUi(date),
                date
            )
        )

        runCatching {
            Log.d("Activity", "date=$date")
            getActivityWithNutritionByDateUseCase(date)
        }.fold(
            onSuccess = {
                emit(
                    ActivityByDayPartialState.ActivityByDayLoaded(activityUi = it?.toUi { s ->
                        DateConverter.dateEntityToActivityByDayUi(s)
                    })
                )

                Log.d("Activity", "is null: ${it == null}")
                val gson = Gson()
                val json = gson.toJson(it)
                Log.d("Activity", json)
            },
            onFailure = {
                Log.d("Activity", "fail: ${it.message}")
            }
        )
    }

}