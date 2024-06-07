package com.example.main.fragments.home.mvi.store

import android.util.Log
import com.example.activity.entity.ActivityEntity
import com.example.activity.usecase.GetActivityByDateUseCase
import com.example.activity.usecase.GetActivityWithNutritionByDateUseCase
import com.example.activity.usecase.GetInitialStepsUseCase
import com.example.activity.usecase.SetInitialStepsUseCase
import com.example.activity.usecase.UpdateActivityUseCase
import com.example.common.mvi.MviActor
import com.example.main.fragments.home.mvi.effect.HomeEffect
import com.example.main.fragments.home.mvi.intent.HomeIntent
import com.example.main.fragments.home.mvi.state.HomePartialState
import com.example.main.fragments.home.mvi.state.HomeState
import com.example.main.utils.DateConverter
import com.example.main.utils.TimeDiff
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class HomeActor @Inject constructor(
    private val updateActivityUseCase: UpdateActivityUseCase,
    private val getActivityByDateUseCase: GetActivityByDateUseCase,
    private val getActivityWithNutritionByDateUseCase: GetActivityWithNutritionByDateUseCase,
    private val getInitialStepsUseCase: GetInitialStepsUseCase,
    private val setInitialStepsUseCase: SetInitialStepsUseCase
) :
    MviActor<HomePartialState, HomeIntent, HomeState, HomeEffect>() {

    override fun resolve(intent: HomeIntent, state: HomeState): Flow<HomePartialState> {
        return when (intent) {
            HomeIntent.GetCurrentDate -> getCurrentDate()
            HomeIntent.OpenNutritionScreen -> flow { _effects.emit(HomeEffect.NavigateToNutritionFragment) }
            HomeIntent.GetActivityWithNutrition -> getActivityWithNutrition()

            is HomeIntent.UpdateStepsInActivity -> updateStepsInActivity(
                state.activityEntity,
                intent.totalSteps
            )

            is HomeIntent.UpdateWaterIntakeInActivity -> updateWaterIntakeInActivity(
                state.activityEntity,
                intent.waterIntake
            )

            is HomeIntent.UpdateSleepTimeInActivity -> updateSleepTimeInActivity(
                state.activityEntity,
                intent.bedtime,
                intent.wakeupTime
            )

            is HomeIntent.UpdateWeightInActivity -> updateWeightInActivity(
                state.activityEntity,
                intent.weight
            )
        }
    }

    private val currentDate: String
        get() {
            val currentDate: Date = Calendar.getInstance().time
            return DateConverter.dateToDateEntity(currentDate)
        }

    private fun getCurrentDate(): Flow<HomePartialState> = flow {
        val currentDate = Calendar.getInstance().time
        val formattedDate = DateConverter.dateToHomeUi(currentDate)
        emit(HomePartialState.CurrentDateLoaded(formattedDate))
    }

    private fun getActivityWithNutrition(): Flow<HomePartialState> = flow {
        runCatching {
            getActivityWithNutritionByDateUseCase(currentDate)
        }.fold(
            onSuccess = {
                emit(HomePartialState.NutritionLoaded(it.nutrition))
                emit(HomePartialState.ActivityLoaded(it.activityEntity))
            },
            onFailure = {
                Log.d("Home", "getActivityWithNutrition failure: ${it.message}")
            }
        )
    }

    private var initialSteps: Int = -1

    private fun updateStepsInActivity(
        activityEntity: ActivityEntity,
        totalSteps: Int
    ): Flow<HomePartialState> = flow {
        if (initialSteps == -1) {
            initialSteps = getInitialStepsUseCase(currentDate)
            if (initialSteps == -1) {
                setInitialStepsUseCase(currentDate, totalSteps)
                return@flow
            }
        }

        runCatching {
            val currentSteps = totalSteps - initialSteps
            val newActivity = activityEntity.copy(stepsNumber = currentSteps)
            updateActivityUseCase(currentDate, newActivity)
            getActivityByDateUseCase(currentDate)
        }.fold(
            onSuccess = {
                emit(HomePartialState.ActivityLoaded(it))
            },
            onFailure = {
                Log.d("Home", "updateStepsInActivity failure: ${it.message}")
            }
        )
    }

    private fun updateWaterIntakeInActivity(
        activityEntity: ActivityEntity,
        waterIntake: Int
    ): Flow<HomePartialState> = flow {
        runCatching {
            val currentWaterIntake = activityEntity.waterIntake
            val newActivityEntity =
                activityEntity.copy(waterIntake = currentWaterIntake + waterIntake)

            updateActivityUseCase(currentDate, newActivityEntity)
            getActivityByDateUseCase(currentDate)
        }.fold(
            onSuccess = {
                emit(HomePartialState.ActivityLoaded(it))
            },
            onFailure = {
                Log.d("Home", "updateWaterIntakeInActivity failure: ${it.message}")
            }
        )
    }

    private fun updateSleepTimeInActivity(
        activityEntity: ActivityEntity,
        bedtime: String,
        wakeupTime: String
    ): Flow<HomePartialState> = flow {
        runCatching {
            val sleepTime = TimeDiff.calculateSleepHours(bedtime, wakeupTime)
            val newActivityEntity = activityEntity.copy(sleepTime = sleepTime)

            updateActivityUseCase(currentDate, newActivityEntity)
            getActivityByDateUseCase(currentDate)
        }.fold(
            onSuccess = {
                emit(HomePartialState.ActivityLoaded(it))
            },
            onFailure = {
                Log.d("Home", "updateSleepTimeInActivity failure: ${it.message}")
            }
        )
    }

    private fun updateWeightInActivity(
        activityEntity: ActivityEntity,
        weight: Float
    ): Flow<HomePartialState> = flow {
        runCatching {
            val newActivityEntity = activityEntity.copy(weight = weight)

            updateActivityUseCase(currentDate, newActivityEntity)
            getActivityByDateUseCase(currentDate)
        }.fold(
            onSuccess = {
                emit(HomePartialState.ActivityLoaded(it))
                Log.d("Home", "getActivityWithNutrition $it")
            },
            onFailure = {
                Log.d("Home", "updateWeightInActivity failure: ${it.message}")
            }
        )
    }
}