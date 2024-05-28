package com.example.auth.fragments.registration.mvi.store

import com.example.auth.fragments.registration.mvi.effect.RegistrationEffect
import com.example.auth.fragments.registration.mvi.intent.RegistrationIntent
import com.example.auth.fragments.registration.mvi.state.RegistrationPartialState
import com.example.auth.fragments.registration.mvi.state.RegistrationState
import com.example.common.mvi.MviStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class RegistrationStore @Inject constructor(
    actor: RegistrationActor,
    reducer: RegistrationReducer
) : MviStore<RegistrationPartialState, RegistrationIntent, RegistrationState, RegistrationEffect>(reducer, actor) {
    override fun initialStateCreator(): RegistrationState {
        return RegistrationState()
    }
}