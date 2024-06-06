package com.example.main.fragments.home.mvi.store

import com.example.activity.entity.ActivityEntity
import com.example.common.mvi.MviStore
import com.example.main.fragments.home.mvi.effect.HomeEffect
import com.example.main.fragments.home.mvi.intent.HomeIntent
import com.example.main.fragments.home.mvi.state.HomePartialState
import com.example.main.fragments.home.mvi.state.HomeState
import com.example.nutrition.entity.NutritionEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeStore @Inject constructor(
    actor: HomeActor,
    reducer: HomeReducer
) : MviStore<HomePartialState, HomeIntent, HomeState, HomeEffect>(
    reducer, actor
) {
    override fun initialStateCreator(): HomeState {
        return HomeState("", NutritionEntity(), ActivityEntity())
    }
}