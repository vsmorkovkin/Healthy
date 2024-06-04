package com.example.main.fragments.home.mvi.store

import com.example.common.mvi.MviActor
import com.example.main.fragments.home.mvi.effect.HomeEffect
import com.example.main.fragments.home.mvi.intent.HomeIntent
import com.example.main.fragments.home.mvi.state.HomePartialState
import com.example.main.fragments.home.mvi.state.HomeState
import com.example.main.utils.DateConverter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Calendar
import javax.inject.Inject


class HomeActor @Inject constructor() :
    MviActor<HomePartialState, HomeIntent, HomeState, HomeEffect>() {

    override fun resolve(intent: HomeIntent, state: HomeState): Flow<HomePartialState> {
        return when (intent) {
            HomeIntent.GetCurrentDate -> getCurrentDate()
            HomeIntent.OpenNutritionScreen -> flow { _effects.emit(HomeEffect.NavigateToNutritionFragment) } }
    }

    private fun getCurrentDate(): Flow<HomePartialState> = flow {
        val currentDate = Calendar.getInstance().time
        val formattedDate = DateConverter.dateToHomeUi(currentDate)
        emit(HomePartialState.CurrentDateLoaded(formattedDate))
    }
}