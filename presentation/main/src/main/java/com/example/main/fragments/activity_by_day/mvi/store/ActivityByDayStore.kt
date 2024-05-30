package com.example.main.fragments.activity_by_day.mvi.store

import com.example.common.mvi.MviStore
import com.example.main.fragments.activity_by_day.mvi.effect.ActivityByDayEffect
import com.example.main.fragments.activity_by_day.mvi.intent.ActivityByDayIntent
import com.example.main.fragments.activity_by_day.mvi.state.ActivityByDayPartialState
import com.example.main.fragments.activity_by_day.mvi.state.ActivityByDayState
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
        return ActivityByDayState(null, null)
    }

}