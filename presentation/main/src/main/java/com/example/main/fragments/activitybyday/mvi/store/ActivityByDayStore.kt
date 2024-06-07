package com.example.main.fragments.activitybyday.mvi.store

import com.example.activity.entity.ActivityWithNutritionEntity
import com.example.common.mvi.MviStore
import com.example.main.fragments.activitybyday.mvi.effect.ActivityByDayEffect
import com.example.main.fragments.activitybyday.mvi.intent.ActivityByDayIntent
import com.example.main.fragments.activitybyday.mvi.state.ActivityByDayPartialState
import com.example.main.fragments.activitybyday.mvi.state.ActivityByDayState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ActivityByDayStore @Inject constructor(
    actor: ActivityByDayActor,
    reducer: ActivityByDayReducer
) : MviStore<ActivityByDayPartialState, ActivityByDayIntent, ActivityByDayState, ActivityByDayEffect>(
    reducer,
    actor
) {

    override fun initialStateCreator(): ActivityByDayState {
        return ActivityByDayState(null, ActivityWithNutritionEntity())
    }

}