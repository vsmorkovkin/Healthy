package com.example.auth.login

import com.example.common.mvi.MviReducer
import javax.inject.Inject

class LoginReducer @Inject constructor() : MviReducer<LoginPartialState, LoginState> {
    override fun reduce(prevState: LoginState, partialState: LoginPartialState): LoginState {
        return LoginState()
    }
}