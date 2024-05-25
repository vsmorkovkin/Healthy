package com.example.auth.login

import com.example.common.mvi.MviActor
import com.example.session.usecase.LoginUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginActor @Inject constructor(
    private val loginUseCase: LoginUseCase
) : MviActor<LoginPartialState, LoginIntent, LoginState, LoginEffect>() {

    override fun resolve(intent: LoginIntent, state: LoginState): Flow<LoginPartialState> {
        return when (intent) {
            is LoginIntent.SignInIntent -> login(intent.email, intent.password)
            LoginIntent.NoAccountIntent -> flow { _effects.emit(LoginEffect.RegisterEffect) }
        }
    }

    private fun login(email: String, password: String): Flow<LoginPartialState> = flow {
        runCatching {
            loginUseCase(email, password)
        }.fold(
            onSuccess = {
                _effects.emit(LoginEffect.SuccessfulLogin)
            },
            onFailure = {
                _effects.emit(LoginEffect.LoginFailure(it.message.orEmpty()))
            }
        )
    }
}