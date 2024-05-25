package com.example.auth.login

import com.example.common.mvi.MviStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginStore @Inject constructor(
    actor: LoginActor,
    reducer: LoginReducer
) : MviStore<LoginPartialState, LoginIntent, LoginState, LoginEffect>(reducer, actor) {
    override fun initialStateCreator(): LoginState {
        return LoginState()
    }
}