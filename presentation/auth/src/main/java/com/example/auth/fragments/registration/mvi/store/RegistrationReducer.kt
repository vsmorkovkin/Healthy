package com.example.auth.fragments.registration.mvi.store

import com.example.auth.fragments.registration.mvi.state.RegistrationPartialState
import com.example.auth.fragments.registration.mvi.state.RegistrationState
import com.example.common.mvi.MviReducer
import javax.inject.Inject

class RegistrationReducer @Inject constructor() :
    MviReducer<RegistrationPartialState, RegistrationState> {
    override fun reduce(
        prevState: RegistrationState,
        partialState: RegistrationPartialState
    ): RegistrationState {
        return RegistrationState()
    }
}